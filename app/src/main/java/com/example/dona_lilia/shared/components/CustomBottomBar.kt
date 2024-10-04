package com.example.dona_lilia.shared.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar (
        modifier = Modifier
            .height(50.dp)
            .clip(shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )),
        containerColor =  MaterialTheme.colorScheme.primary,
    ) {

    }
}