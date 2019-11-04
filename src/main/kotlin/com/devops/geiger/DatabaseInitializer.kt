package com.devops.geiger

import com.devops.geiger.entity.Device
import com.devops.geiger.repository.DeviceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class DatabaseInitializer(
        @Autowired val repository: DeviceRepository
) {

    @PostConstruct
    fun initialize(){
        repository.run {
            deleteAll()
            save(Device(12345))
            save(Device(23456))
            save(Device(34567))
            save(Device(43234))
            save(Device(5252454))
            save(Device(123232))

        }
    }
}