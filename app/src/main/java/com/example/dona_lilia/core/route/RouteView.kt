package com.example.dona_lilia.core.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dona_lilia.features.views.CreateOrderView
import com.example.dona_lilia.features.views.HomeView
import com.example.dona_lilia.features.views.TicketDetails
import com.example.dona_lilia.features.views.TicketList

@Composable
fun RouteView () {

    val navController = rememberNavController()

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
            route = ItemNavigation.TicketDetailsView.route
        ) {
            TicketDetails(
                navController = navController
            )
        }

        // -> TicketListView
        composable(
            route = ItemNavigation.TicketListView.route
        ) {
            TicketList(
                navController = navController
            )
        }
    }
}