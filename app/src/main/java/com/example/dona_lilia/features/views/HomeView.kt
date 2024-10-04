package com.example.dona_lilia.features.views


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dona_lilia.shared.components.CusttomLayout

@Composable
fun HomeView (

    navController : NavController

) {
    CusttomLayout(

        title = "INICIO"

    ) {

        Text(text = "Inicioss")

    }
}