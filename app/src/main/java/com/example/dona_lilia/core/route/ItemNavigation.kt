package com.example.dona_lilia.core.route

import com.example.dona_lilia.core.enums.Routes

sealed class ItemNavigation ( val route : String ) {
    data object  HomeView : ItemNavigation (Routes.HomeView.name)
    data object  CreateOrderView : ItemNavigation (Routes.CreateOrderView.name)
    data object  TicketListView : ItemNavigation (Routes.TicketListView.name)
    data object  TicketDetailsView : ItemNavigation (Routes.TicketDetailsView.name)
    data object  ProductsView : ItemNavigation (Routes.ProductsView.name)
    data object  CreateProductView : ItemNavigation (Routes.CreateProductView.name)
}