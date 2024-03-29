package com.example.flightapp.model

import java.io.Serializable

data class Transaction(
    val id: Int,
    var date: String? = null,
    var baggage: String? = null,
    var seatNumber: String? = null,
    val user: User,
    val flight: Flight,
) : Serializable
