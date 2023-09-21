package net.quazar.logisticsassistant.data.model

import java.time.LocalDate
import java.time.LocalTime

data class TaskDescription(
    val cargoType: String,
    val executionCity: String,
    val orderDate: LocalDate,
    val arrivalTime: LocalTime,
    val routeStart: String,
    val routeEnd: String,
    val bodyType: String,
    val orderDetails: String,
    val paymentOptions: String,
    val contactsPhone: String,
    val contactsFullName: String
)
