package com.devops.geiger.dto

import com.devops.geiger.entity.Device
import com.devops.geiger.entity.Measurement
import org.springframework.web.util.UriComponentsBuilder


object MeasurementTransformer {

   fun transform(measurement: Measurement) : MeasurementDto{
       return MeasurementDto(measurement.latitude, measurement.longitude, measurement.sievert, measurement.createdDate, measurement.id)
   }

    fun transform(measurements: Iterable<Measurement>) : List<MeasurementDto> {
        return measurements
                .map { transform(it) }
                .toMutableList()
    }



}
object DeviceTransformer {
    fun transform(device: Device) : DeviceDto {
        return DeviceDto(device.modelNumber, device.createdDate, device.id)
    }


    fun transform(devices : Iterable<Device>) : List<DeviceDto> {
        return devices
                .map { transform(it) }
                .toMutableList()
    }
}