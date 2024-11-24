package com.example.dona_lilia.features.models

// Estructura para mantener la selección del usuario
data class ProductSelection(
    val product: ProductItem,
    val size: String,
    val quantity: Int,
    val unitPrice: Double
)