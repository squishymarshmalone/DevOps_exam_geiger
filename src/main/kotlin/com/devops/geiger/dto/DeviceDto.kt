package com.devops.geiger.dto

import java.time.LocalDateTime

data class DeviceDto(

        var modelNumber: Int,

        var createdDate: LocalDateTime? = null,

        var id: Long? = null
)