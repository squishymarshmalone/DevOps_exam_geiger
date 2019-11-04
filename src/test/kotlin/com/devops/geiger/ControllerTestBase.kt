package com.devops.geiger

import com.devops.geiger.repository.DeviceRepository
import com.devops.geiger.repository.MeasurementRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes=[(Application::class)],
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ControllerTestBase {

    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var deviceRepo : DeviceRepository
    @Autowired
    protected lateinit var measurementRepo : MeasurementRepository


    @BeforeEach
    fun setupRestAssured(){
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }
    @BeforeEach
    @AfterEach
    fun clean() {
        deviceRepo.deleteAll()
        measurementRepo.deleteAll()
    }
}