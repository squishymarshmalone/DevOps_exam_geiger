package com.devops.geiger

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile


@Profile("!local")
@Configuration
class MicrometerConfig {
    @Primary
    @Bean
    fun meterRegistry(): MeterRegistry{
        return SimpleMeterRegistry()
    }
}