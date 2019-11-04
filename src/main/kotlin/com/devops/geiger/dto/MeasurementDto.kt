package com.devops.geiger.dto

import java.time.LocalDateTime

data class MeasurementDto(
        var latitude: Double,

        var longitude: Double,

        var sievert: Int,

        var createdDate: LocalDateTime? = null,

        var id: Long? = null
)