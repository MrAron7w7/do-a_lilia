package com.example.dona_lilia.features.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.dona_lilia.shared.components.*

@Composable
fun CreateOrderView(
    navController: NavController
) {
    CusttomLayout(
        title = "CREAR PEDIDO"
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomLabel(
                    text = "DATOS DEL CLIENTE",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W600
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Inputs for customer details
                CustomerInputFields()

                Spacer(modifier = Modifier.height(20.dp))

                CustomLabel(
                    text = "PRODUCTOS SOLICITADOS",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Carousel of products
                ProductCarousel()

                Spacer(modifier = Modifier.height(20.dp))

                CustomLabel(
                    text = "RELEVANCIA",
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomTextButton(
                        text = "1",
                        color = Color(0xff5CD149),
                        onClick = {},
                    )
                    CustomTextButton(
                        text = "2",
                        color = Color(0xffEA682D),
                        onClick = {},
                    )
                    CustomTextButton(
                        text = "3",
                        color = Color(0xffFE0F0C),
                        onClick = {},
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(text = "CREAR") {
                    // Action to create order
                }

                CustomButton(text = "ATRAS") {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun CustomerInputFields() {
    // Helper composable for repeated input fields
    val fields = listOf("Nombre", "Referencia", "Telefono", "R.U.C", "Destino", "Fecha E")
    fields.forEach { label ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomLabel(text = "$label:")
            Spacer(modifier = Modifier.width(10.dp))
            CustomInput()
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun ProductCarousel() {
    val products = listOf("Lejía", "Producto 2", "Producto 3")
    var selectedOption = remember { mutableStateOf<Map<String, Int>>(mapOf()) }
    val options = listOf("200 ml", "400 ml", "100 ml")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(products) { product ->
            ProductCard(product, options, selectedOption)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    product: String,
    options: List<String>,
    selectedOption: MutableState<Map<String, Int>>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Imagen del producto
        Image(
            painter = rememberImagePainter("https://via.placeholder.com/100"), // Placeholder URL
            contentDescription = "$product image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.Blue, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del producto
        Text(
            text = product,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Opciones de tamaño usando FlowRow con los parámetros correctos
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,  // Equivalente a mainAxisAlignment
            verticalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre filas
            maxItemsInEachRow = 3 // Ajusta según tus necesidades
        ) {
            options.forEachIndexed { index, option ->
                val isSelected = selectedOption.value[product] == index
                val optionColor = if (isSelected) Color(0xFF3A5DF7) else Color(0xFFD1D1D1)
                val textColor = if (isSelected) Color.White else Color.Black

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(optionColor)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clickable {
                            selectedOption.value = selectedOption.value.toMutableMap()
                                .apply { put(product, index) }
                        }
                ) {
                    Text(
                        text = option,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
