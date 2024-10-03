package com.example.dona_lilia.features.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dona_lilia.shared.components.CustomLabel

@Composable
fun TicketList (
    navController : NavController
) {
    // TODO: Implement ticket list UI here
    Scaffold { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            CustomLabel( text = "List")
        }
    }
}