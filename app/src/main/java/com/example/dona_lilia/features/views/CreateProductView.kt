package com.example.dona_lilia.features.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dona_lilia.features.models.Products
import com.example.dona_lilia.features.services.FirebaseService
import com.example.dona_lilia.shared.components.CustomButton
import com.example.dona_lilia.shared.components.CustomInput
import com.example.dona_lilia.shared.components.CusttomLayout

@Composable
fun CreateProductView(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unitPrice by remember { mutableStateOf("") }
    val db = FirebaseService()

    CusttomLayout(title = "Agregar Producto") {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card (
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(5.dp),
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Nombre:")
                CustomInput(value = name) {
                    name = it
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Cantidad:")
                CustomInput(value = quantity) {
                    quantity = it
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Precio Unitario:")
                CustomInput(value = unitPrice) {
                    unitPrice = it
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CustomButton(text = "Agregar") {
                    if (name.isNotEmpty() || quantity.isNotEmpty() || unitPrice.isNotEmpty()) {
                        val product = Products(
                            productName = name,
                            quantity = quantity.toInt(),
                            unitPrice = unitPrice.toDouble()
                        )
                        db.addProduct(product)
                        navController.popBackStack()
                    }
                }
                CustomButton(text = "  Atras  ") {
                    navController.popBackStack()
                }
            }
        }
    }
}