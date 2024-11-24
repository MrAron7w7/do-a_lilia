package com.example.dona_lilia.features.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import com.example.dona_lilia.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.ProductionQuantityLimits
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
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
    var selectedImage by remember { mutableStateOf<String?>(null) }
    var showImageSelector by remember { mutableStateOf(false) } // Controla la visibilidad del selector

    val db = FirebaseService()
    val availableImages = listOf(
        "Lejia" to R.drawable.lejia,
        "Limpia Todo" to R.drawable.limpiatodo,
        "Pino Natural" to R.drawable.pino,
        "Jabón Líquido" to R.drawable.jabon,
        "Alcohol" to R.drawable.alcohol
    )

    CusttomLayout(title = "Agregar Producto") {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Selección de imagen
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier
                    .size(150.dp)
                    .clickable { showImageSelector = true } // Muestra el selector de imágenes
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (selectedImage == null) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Seleccionar Imagen",
                            modifier = Modifier.size(64.dp)
                        )
                    } else {
                        // Mostrar la imagen seleccionada
                        androidx.compose.foundation.Image(
                            painter = painterResource(id = availableImages.first { it.first == selectedImage }.second),
                            contentDescription = "Imagen seleccionada",
                            modifier = Modifier.size(128.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Campo: Nombre
            CustomInput(
                value = name,
                label = "Nombre",
                imageVector = Icons.Filled.ProductionQuantityLimits,
                onValueChange = { name = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Campo: Cantidad
            CustomInput(
                value = quantity,
                label = "Cantidad",
                keyboardType = KeyboardType.Number,
                imageVector = Icons.Filled.Numbers,
                onValueChange = { quantity = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Campo: Precio Unitario
            CustomInput(
                value = unitPrice,
                label = "Precio Unitario",
                keyboardType = KeyboardType.Number,
                imageVector = Icons.Filled.PriceCheck,
                onValueChange = { unitPrice = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CustomButton(text = "Agregar") {
                    if (name.isNotEmpty() && quantity.isNotEmpty() && unitPrice.isNotEmpty() && selectedImage != null) {
                        val product = Products(
                            productName = name,
                            quantity = quantity.toInt(),
                            unitPrice = unitPrice.toDouble()
                        )
                        db.addProduct(product)
                        Toast.makeText(
                            navController.context,
                            "Producto añadido",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            navController.context,
                            "Completa todos los campos e incluye una imagen.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                CustomButton(text = "Atrás") {
                    navController.popBackStack()
                }
            }
        }

        // Selector de imágenes
        if (showImageSelector) {
            ImageSelectorDialog(
                availableImages = availableImages,
                onImageSelected = { imageName ->
                    selectedImage = imageName
                    showImageSelector = false
                },
                onDismiss = { showImageSelector = false }
            )
        }
    }
}

@Composable
fun ImageSelectorDialog(
    availableImages: List<Pair<String, Int>>,
    onImageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Selecciona una Imagen")
        },
        text = {
            Column {
                availableImages.forEach { (name, resourceId) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onImageSelected(name) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.foundation.Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = name)
                    }
                }
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}
