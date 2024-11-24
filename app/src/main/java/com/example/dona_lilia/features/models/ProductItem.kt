package com.example.dona_lilia.features.models

data class ProductItem(
    val name: String,
    val imageResource: Int, // Cambiado de String a Int
    val prices: Map<String, Double> = mapOf(
        "200 ml" to 5.0,
        "400 ml" to 8.0,
        "100 ml" to 3.0
    )
)