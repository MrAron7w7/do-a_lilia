package com.example.dona_lilia.features.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dona_lilia.core.route.ItemNavigation
import com.example.dona_lilia.features.models.Products
import com.example.dona_lilia.features.services.FirebaseService
import com.example.dona_lilia.shared.components.CustomButton
import com.example.dona_lilia.shared.components.CusttomLayout
import com.example.dona_lilia.shared.theme.colorcard

@Composable
fun ProductsView(navController: NavController) {
    val productsList = remember { mutableStateListOf<Products>() }

    LaunchedEffect(true) {
        FirebaseService().fetchProducts().collect { productList ->
            productsList.clear()
            productsList.addAll(productList)
        }
    }
    CusttomLayout(title = "Productos") {

        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(productsList) { product ->
                    ProductListItem(products = product)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(text = "Atras") {
                navController.popBackStack()
            }

            CustomButton(text = "Agregar") {
                navController.navigate(ItemNavigation.CreateProductView.route)
            }
        }

    }
}

@Composable
fun ProductListItem(products: Products){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = colorcard)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = products.productName,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Text(text = "Cantidad: ${products.quantity}")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = products.unitPrice.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}