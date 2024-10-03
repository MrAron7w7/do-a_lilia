package com.example.dona_lilia.features.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dona_lilia.shared.components.CustomCard
import com.example.dona_lilia.shared.components.CustomLabel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView (
    navController : NavController
) {
    Scaffold (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            CenterAlignedTopAppBar(
                title = { CustomLabel( text = "INICIO") },
                colors =  TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.background
                    ),
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
            )

        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Cards de los botones
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCard(
                    modifier = Modifier
                        .weight(1f),
                    text = "Nuevo Pedido",
                )
                CustomCard(
                    modifier = Modifier
                        .weight(1f),
                    text = "Boletas",
                )
            }
        }
    }
}