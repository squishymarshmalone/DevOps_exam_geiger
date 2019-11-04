package com.devops.geiger.entity


import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Measurement(

        @get:NotNull
        @get:ManyToOne
        var device: Device,

        var latitude: Double,

        var longitude: Double,

        var sievert: Int,

        @get:CreatedDate
        @get:Column(name = "created_date", nullable = false, updatable = false)
        var createdDate: LocalDateTime? = null,

        @get:Id @get:GeneratedValue
        var id: Long? = null
)