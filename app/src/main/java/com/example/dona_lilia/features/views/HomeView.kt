package com.example.dona_lilia.features.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dona_lilia.R
import com.example.dona_lilia.core.route.ItemNavigation
import com.example.dona_lilia.features.services.FirebaseService
import com.example.dona_lilia.shared.components.CustomLabel
import com.example.dona_lilia.shared.components.CusttomLayout

@Composable
fun HomeView (

    navController : NavController

) {
    val db = FirebaseService()
    CusttomLayout(

        title = "INICIO"

    ) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            // Imagen - Logo
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
            )


            // Cards de seleccion
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                CustomCardHome(
                    text = "Gestion Clientes",
                    painter = painterResource(id = R.drawable.people)
                ) {
                    //navController.navigate(ItemNavigation.CreateOrderView.route)
                }

                CustomCardHome(
                    text = "Nuevo Pedido",
                    painter = painterResource(id = R.drawable.add_circle)
                ) {
                    navController.navigate(ItemNavigation.CreateOrderView.route)
                }
            }


            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                CustomCardHome(
                    text = "Productos",
                    painter = painterResource(id = R.drawable.box_card)
                ) {
                    navController.navigate(ItemNavigation.ProductsView.route)
                }

                CustomCardHome(
                    text = "Lista de Boletas",
                    painter = painterResource(id = R.drawable.description)
                ) {
                    navController.navigate(ItemNavigation.TicketListView.route)
                }
            }


        }

    }
}

@Composable
fun CustomCardHome (
    text : String,
    painter : Painter,
    onClick: () -> Unit,
) {
    Card (
        onClick = onClick,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painter,
                contentDescription = "",
                modifier = Modifier.size(100.dp),
                tint = Color.Black
            )

            CustomLabel(
                text = text,
                fontWeight = FontWeight.Bold
            )
        }
    }
}