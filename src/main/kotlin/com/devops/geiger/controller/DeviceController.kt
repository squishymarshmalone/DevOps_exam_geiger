package com.devops.geiger.controller

import com.devops.geiger.Application
import com.devops.geiger.dto.DeviceDto
import com.devops.geiger.dto.DeviceTransformer
import com.devops.geiger.entity.Device
import com.devops.geiger.repository.DeviceRepository
import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.slf4j.Logger
import io.micrometer.core.instrument.Gauge




@RequestMapping(path = ["/devices"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@RestController
class DeviceController(
        val repository: DeviceRepository,
        private val meterRegistry: MeterRegistry
) {

    private final var logger : Logger = LoggerFactory.getLogger(this::class.java)!!

    init {
        if(meterRegistry.javaClass.kotlin == SimpleMeterRegistry::class){
            logger.warn("Type of meter registry is SimpleMeterRegistry. Application should be configured with a time series database.")
        }
    }

    @Timed
    @GetMapping
    fun getAll(): ResponseEntity<List<DeviceDto>> {
        logger.debug("User requested all devices.")

        val devices = repository.findAll()

        logger.debug("Returning all ${devices.count()} devices.")
        meterRegistry.counter("api.response", "ok", "devices").increment()

        meterRegistry.gauge("number.devices", devices.count())

        return ResponseEntity.ok(DeviceTransformer.transform(devices))
    }


    @Timed
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(
            @RequestBody
            deviceDto: DeviceDto
    ): ResponseEntity<DeviceDto> {


        DistributionSummary
                .builder("request.body.size")
                .tags("POST", "device")
                .register(meterRegistry)
                .record(deviceDto.toString().length.toDouble()) //this is pretty silly, but it illstrates a potential use case for distribution summary

        if (deviceDto.id != null) {
            meterRegistry.counter("http.requests", "uri", "/devices")
            logger.debug("User attempted to create a device by supplying an ID. Responding with bad request.")
            return ResponseEntity.badRequest().build()
        }

        val device = Device(deviceDto.modelNumber)
        val persisted = repository.save(device)
        logger.debug("Created a new device: ${device.id}")
        meterRegistry.counter("api.response", "created", "device").increment()



        return ResponseEntity.ok(DeviceTransformer.transform(persisted))
    }
}