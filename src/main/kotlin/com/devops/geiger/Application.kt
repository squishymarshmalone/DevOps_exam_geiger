package com.devops.geiger

import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application {

}


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)

    val logger = LoggerFactory.getLogger(Application::class.java)

    logger.info("Started application.")



}
