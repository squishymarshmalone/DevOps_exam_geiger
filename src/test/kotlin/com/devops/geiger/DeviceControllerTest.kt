package com.devops.geiger

import com.devops.geiger.dto.DeviceDto
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class DeviceControllerTest : ControllerTestBase() {

    /*@Test
    fun ensureTravisDoesNotDeployToHerokuOnFailingTests(){
        assert(false);
    }*/

    @Test
    fun testEmptyDB() {
        given().get("/devices").then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(0))
    }

    @Test
    fun testCreateDevice(){

        val deviceDto = DeviceDto(12345)

        val result = given().contentType(ContentType.JSON)
                .body(deviceDto)
                .post("/devices")
                .then()
                .statusCode(200)

        val getResult = given().
                get("/devices").
                then().
                statusCode(200).
                body("size()", CoreMatchers.equalTo(1))
    }


    @Test
    fun testCannotCreateDeviceWithId(){

        val deviceDto = DeviceDto(12345)
        deviceDto.id = 123

        val result = given().contentType(ContentType.JSON)
                .body(deviceDto)
                .post("/devices")
                .then()
                .statusCode(400)

        val getResult = given().
                get("/devices").
                then().
                statusCode(200).
                body("size()", CoreMatchers.equalTo(0))

    }

}