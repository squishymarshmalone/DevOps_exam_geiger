package com.devops.geiger



import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class MeasurementController : ControllerTestBase() {

    @Test
    fun testEmptyDB() {
        given().get("/devices").then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(0))
    }

    @Test
    fun testCannotRetrieveMeasurementsFromNonexistingDevice(){

        val invalidDeviceId = 123

        val getResult = given().
                get("/devices/$invalidDeviceId/measurements").
                then().
                statusCode(200)
                .body("size()", CoreMatchers.equalTo(0))

    }
}