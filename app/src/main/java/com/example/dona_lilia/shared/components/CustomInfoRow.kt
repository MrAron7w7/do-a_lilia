package com.example.dona_lilia.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InfoRow(
    label: String,
    value: String,
    color: Color
) {
    Column {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = color.copy(alpha = 0.7f)
            )
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = color
            )
        )
    }
}