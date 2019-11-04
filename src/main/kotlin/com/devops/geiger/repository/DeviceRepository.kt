package com.devops.geiger.repository

import com.devops.geiger.entity.Device
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface DeviceRepository : CrudRepository<Device, Long>
