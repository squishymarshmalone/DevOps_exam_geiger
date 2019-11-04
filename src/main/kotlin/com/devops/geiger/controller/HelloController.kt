package com.devops.geiger.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @RequestMapping(value = ["/"])
    fun helloWorld() = "Welcome."
}