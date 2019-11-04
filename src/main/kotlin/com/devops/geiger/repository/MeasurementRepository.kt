package com.devops.geiger.repository


import com.devops.geiger.entity.Measurement
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MeasurementRepository : CrudRepository<Measurement, Long> {

    fun findAllByDeviceId(deviceId: Long): Iterable<Measurement>

}
