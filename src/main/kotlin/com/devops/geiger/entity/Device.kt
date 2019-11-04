package com.devops.geiger.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Device(

        @get:NotNull
        var modelNumber: Int,

        @CreatedDate
        @Column(name = "created_date", nullable = false, updatable = false)
        var createdDate: LocalDateTime? = null,

        @get:Id @get:GeneratedValue
        var id: Long? = null
)