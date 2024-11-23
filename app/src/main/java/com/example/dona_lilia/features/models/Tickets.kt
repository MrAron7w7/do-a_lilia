package com.example.dona_lilia.features.models

import java.util.Date

data class Tickets(
    val cod: String = "",
    val clientName: String = "",
    val reference: String = "",
    val phoneNumber: String = "",
    val ruc: String = "",
    val destination: String = "",
    val deliveryDate: Date = Date(),
    val products: List<Products> = emptyList(),
    val totalAmount: Double = 0.0,
    val relevance: String = ""
)
