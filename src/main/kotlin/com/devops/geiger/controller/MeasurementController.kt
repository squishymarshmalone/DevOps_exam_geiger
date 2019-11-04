package com.devops.geiger.controller

import com.devops.geiger.dto.MeasurementDto
import com.devops.geiger.dto.MeasurementTransformer
import com.devops.geiger.entity.Device
import com.devops.geiger.entity.Measurement
import com.devops.geiger.repository.DeviceRepository
import com.devops.geiger.repository.MeasurementRepository
import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Required
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*




@RequestMapping(path = ["/devices"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@RestController
class MeasurementController(
        val repository: MeasurementRepository,
        val deviceRepository: DeviceRepository,
        val meterRegistry: MeterRegistry

) {


    val logger = LoggerFactory.getLogger(this::class.java)!!


    @Timed
    @GetMapping(path = ["{deviceId}/measurements"])
    fun getAll(
            @PathVariable("deviceId")
            deviceId: Long?,
            @RequestParam("minSievert")
            minSievert: Int?

    ): ResponseEntity<Iterable<MeasurementDto>> {

        if(deviceId == null){
            logger.info("User requested measurements without specifying a device ID. Returning 400.")
            return ResponseEntity.badRequest().build()
        }

        var measurements = repository.findAllByDeviceId(deviceId)
        logger.debug("Returning ${measurements.count()} measurements for device $deviceId")
        meterRegistry.counter("api.response", "ok", "measurement").increment()


        if(minSievert != null){
            //this should probably be done with an apropriate SQL query, however to have an example of a task that could take some time (given a large dataset) I decided to allow for this filter.
            meterRegistry.more().longTaskTimer("measurements.filter.sievert").recordCallable {
                measurements = measurements.filter { it.sievert >= minSievert }
            }
        }


        return ResponseEntity.ok(MeasurementTransformer.transform(measurements))
    }


    @Timed
    @PostMapping(path = ["{deviceId}/measurements"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun create(
            @PathVariable("deviceId")
            deviceId: Long?,
            @RequestBody
            measurementDto: MeasurementDto
    ): ResponseEntity<MeasurementDto> {


        if (measurementDto.id != null || deviceId == null) {
            return ResponseEntity.badRequest().build()
        }


        val device = deviceRepository.findByIdOrNull(deviceId)

        if(device == null){
            logger.info("User attempting to add measurements for a device that does not exist")
            return ResponseEntity.notFound().build()
        }

        val measurement = Measurement(device, measurementDto.latitude, measurementDto.longitude, measurementDto.sievert)
        val persistedMeasurement = repository.save(measurement)
        logger.debug("Created measurement with id ${measurement.id} for device $deviceId")

        meterRegistry.counter("api.response", "created", "measurement").increment()


        return ResponseEntity.ok(MeasurementTransformer.transform(persistedMeasurement))
    }
}