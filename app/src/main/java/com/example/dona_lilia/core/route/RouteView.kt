package com.example.dona_lilia.core.route

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.features.services.FirebaseService
import com.example.dona_lilia.features.views.CreateOrderView
import com.example.dona_lilia.features.views.HomeView
import com.example.dona_lilia.features.views.ProductsView
import com.example.dona_lilia.features.views.TicketDetails
import com.example.dona_lilia.features.views.TicketList

@Composable
fun RouteView () {
    val context = LocalContext.current
    val navController = rememberNavController()
    val firebaseService = FirebaseService()
    var tickets by remember { mutableStateOf(listOf<Tickets>()) }

    LaunchedEffect(Unit) {
        firebaseService.fetchTickets().collect { fetchedTickets ->
            tickets = fetchedTickets
        }
    }

    NavHost(
        navController = navController,
        startDestination = ItemNavigation.HomeView.route
    ) {

        // Vista de inicio de la aplicacion -> HomeView
        composable(
            route = ItemNavigation.HomeView.route
        ) {
            HomeView(
                navController = navController
            )
        }

        // -> CreateOrderView
        composable(
            route = ItemNavigation.CreateOrderView.route
        ) {
            CreateOrderView(
                navController = navController
            )
        }

        // -> TicketDetailsView
        composable(
            route = "ticket_details/{ticketId}"
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId") ?: ""
            val ticket = tickets.firstOrNull { it.cod == ticketId }

            if (ticket != null) {
                TicketDetails(
                    ticket = ticket,
                    navController = navController
                )
            } else {
                // Manejo de error si no se encuentra el ticket
                Toast.makeText(context, "Ticket no encontrado", Toast.LENGTH_SHORT).show()
            }
        }


        // -> TicketListView
        composable(
            route = ItemNavigation.TicketListView.route
        ) {
            TicketList(
                navController = navController
            )
        }

        // -> ProductsView
        composable(
            route = ItemNavigation.ProductsView.route
        ) {
            ProductsView(navController)
        }
    }
}