package com.example.dona_lilia.features.views


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dona_lilia.R
import com.example.dona_lilia.features.models.ProductItem
import com.example.dona_lilia.features.models.ProductSelection
import com.example.dona_lilia.features.models.Products
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.features.models.Users
import com.example.dona_lilia.features.services.FirebaseService
import com.example.dona_lilia.shared.components.*
import java.util.Calendar
import java.util.Date


@Composable
fun CreateOrderView(
    navController: NavController
) {
    // Estados existentes
    var name = remember { mutableStateOf("") }
    var reference = remember { mutableStateOf("") }
    var phoneNumber = remember { mutableStateOf("") }
    var ruc = remember { mutableStateOf("") }
    var destination = remember { mutableStateOf("") }
    var date = remember { mutableStateOf("") }
    var relevance = remember { mutableStateOf("verde") }
    var selectedProducts = remember { mutableStateOf<List<ProductSelection>>(emptyList()) }

    val context = LocalContext.current
    val db = FirebaseService()

    CusttomLayout(title = "Nuevo Pedido") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card para datos del cliente
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Datos del Cliente",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        CustomerInputFields(
                            name = name,
                            reference = reference,
                            phoneNumber = phoneNumber,
                            ruc = ruc,
                            destination = destination,
                            date = date
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Card para selección de productos
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Productos Solicitados",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        ProductCarousel(
                            onProductsSelected = { products ->
                                selectedProducts.value = products
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Card para relevancia
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Flag,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Relevancia del Pedido",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                RelevanceButton(
                                    text = "Normal",
                                    color = Color(0xff5CD149),
                                    isSelected = relevance.value == "verde",
                                    onClick = { relevance.value = "verde" }
                                )
                                RelevanceButton(
                                    text = "Importante",
                                    color = Color(0xffEA682D),
                                    isSelected = relevance.value == "amarillo",
                                    onClick = { relevance.value = "amarillo" }
                                )

                            }
                            
                            Spacer(modifier = Modifier.height(20.dp))

                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                RelevanceButton(
                                    text = "Urgente",
                                    color = Color(0xffFE0F0C),
                                    isSelected = relevance.value == "rojo",
                                    onClick = { relevance.value = "rojo" }
                                )
                            }
                        }


                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botones de acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancelar", fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            if (validateForm(name.value, phoneNumber.value)) {
                                createOrder(
                                    context,
                                    db,
                                    name.value,
                                    reference.value,
                                    phoneNumber.value,
                                    ruc.value,
                                    destination.value,
                                    date.value,
                                    selectedProducts.value,
                                    relevance.value,
                                    navController
                                )
                            } else {
                                Toast.makeText(context, "Por favor, completa todos los campos requeridos", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Crear Pedido", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun RelevanceButton(
    text: String,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) color.copy(alpha = 0.2f) else Color.Transparent)
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            color = color,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun CustomInput(
    label: String,
    value: String,
    imageVector: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F7FA),
                focusedContainerColor = Color(0xFFF5F7FA),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    product: ProductItem,
    onProductSelected: (ProductSelection) -> Unit
) {
    var selectedSize = remember { mutableStateOf<String?>(null) }
    var quantity = remember { mutableStateOf(0) }

    val prices = mapOf(
        "200 ml" to 5.0,
        "400 ml" to 8.0,
        "100 ml" to 3.0
    )

    Card(
        modifier = Modifier
            .width(220.dp)
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F7FA))
            ) {
                Image(
                    painter = painterResource(id = product.imageResource),
                    contentDescription = "${product.name} image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Selector de tamaño con chips
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                prices.keys.forEach { size ->
                    SizeChip(
                        size = size,
                        isSelected = selectedSize.value == size,
                        onClick = {
                            selectedSize.value = size
                            if (quantity.value > 0) {
                                onProductSelected(
                                    ProductSelection(
                                        product = product,
                                        size = size,
                                        quantity = quantity.value,
                                        unitPrice = prices[size] ?: 0.0
                                    )
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Control de cantidad
            QuantityControl(
                quantity = quantity.value,
                onQuantityChange = { newQuantity ->
                    quantity.value = newQuantity
                    if (selectedSize.value != null) {
                        onProductSelected(
                            ProductSelection(
                                product = product,
                                size = selectedSize.value!!,
                                quantity = newQuantity,
                                unitPrice = prices[selectedSize.value] ?: 0.0
                            )
                        )
                    }
                }
            )

            if (selectedSize.value != null && quantity.value > 0) {
                val unitPrice = prices[selectedSize.value] ?: 0.0
                val total = unitPrice * quantity.value
                Text(
                    text = "Total: S/. ${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun SizeChip(
    size: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else Color(0xFFF5F7FA)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = size,
            color = if (isSelected) Color.White else Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun QuantityControl(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFF5F7FA),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Reducir cantidad",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Text( text = quantity.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(
            onClick = { onQuantityChange(quantity + 1) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Aumentar cantidad",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    label: String,
    selectedDate: MutableState<String>
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePicker = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formattedDate = String.format("%02d-%02d-%d", dayOfMonth, month + 1, year)
            selectedDate.value = formattedDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.minDate = System.currentTimeMillis()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F7FA))
                .clickable { datePicker.show() }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = if (selectedDate.value.isEmpty()) "Seleccionar fecha" else selectedDate.value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedDate.value.isEmpty())
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    else
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

// Funciones auxiliares
private fun validateForm(name: String, phoneNumber: String): Boolean {
    return name.isNotBlank() && phoneNumber.isNotBlank()
}

private fun createOrder(
    context: Context,
    db: FirebaseService,
    name: String,
    reference: String,
    phoneNumber: String,
    ruc: String,
    destination: String,
    date: String,
    selectedProducts: List<ProductSelection>,
    relevance: String,
    navController: NavController
) {
    val ticketProducts = selectedProducts.map { selection ->
        Products(
            productName = selection.product.name,
            quantity = selection.quantity,
            unitPrice = selection.unitPrice
        )
    }

    val totalAmount = selectedProducts.sumOf { selection ->
        selection.quantity * selection.unitPrice
    }

    val ticket = Tickets(
        cod = "T${System.currentTimeMillis()}",
        clientName = name,
        reference = reference,
        phoneNumber = phoneNumber,
        ruc = ruc,
        destination = destination,
        deliveryDate = Date(),
        products = ticketProducts,
        totalAmount = totalAmount,
        relevance = relevance
    )

    val users = Users(
        name = name,
        destine = destination,
        referece = reference,
        ruc = ruc,
        phone = phoneNumber,
        date = date,
    )

    // Guardar en Firebase
    db.addUser(users)
    db.addTicket(ticket)

    Toast.makeText(context, "Pedido creado exitosamente", Toast.LENGTH_LONG).show()
    navController.popBackStack()
}

@Composable
fun ProductCarousel(
    onProductsSelected: (List<ProductSelection>) -> Unit
) {
    val products = listOf(
        ProductItem("Lejia", R.drawable.lejia),
        ProductItem("Limpia todo", R.drawable.limpiatodo),
        ProductItem("Pino Natural", R.drawable.pino),
        ProductItem("Jabon Liquido", R.drawable.jabon),
        ProductItem("Alcohol", R.drawable.alcohol)
    )

    var selectedProducts by remember { mutableStateOf<List<ProductSelection>>(emptyList()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onProductSelected = { selection ->
                        // Actualizar la lista de productos seleccionados
                        selectedProducts = selectedProducts
                            .filterNot { it.product.name == selection.product.name }
                            .plus(selection)
                            .filter { it.quantity > 0 }

                        onProductsSelected(selectedProducts)
                    }
                )
            }
        }

        if (selectedProducts.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            // Resumen de productos seleccionados
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF5F7FA),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Resumen del pedido",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                selectedProducts.forEach { selection ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${selection.product.name} (${selection.size})",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "${selection.quantity} x S/. ${"%.2f".format(selection.unitPrice)} = S/. ${"%.2f".format(selection.quantity * selection.unitPrice)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "S/. ${"%.2f".format(selectedProducts.sumOf { it.quantity * it.unitPrice })}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun CustomerInputFields(
    name: MutableState<String>,
    reference: MutableState<String>,
    phoneNumber: MutableState<String>,
    ruc: MutableState<String>,
    destination: MutableState<String>,
    date: MutableState<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomInput(
            label = "Nombre del Cliente*",
            value = name.value,
            imageVector = Icons.Default.Person,
            onValueChange = { name.value = it }
        )

        CustomInput(
            label = "Referencia",
            value = reference.value,
            imageVector = Icons.Default.Bookmark,
            onValueChange = { reference.value = it }
        )

        CustomInput(
            label = "Teléfono*",
            value = phoneNumber.value,
            imageVector = Icons.Default.Phone,
            keyboardType = KeyboardType.Phone,
            onValueChange = { phoneNumber.value = it }
        )

        CustomInput(
            label = "RUC",
            value = ruc.value,
            imageVector = Icons.Default.Numbers,
            keyboardType = KeyboardType.Number,
            onValueChange = { ruc.value = it }
        )

        CustomInput(
            label = "Destino",
            value = destination.value,
            imageVector = Icons.Default.Place,
            onValueChange = { destination.value = it }
        )

        DatePickerField(
            label = "Fecha de entrega",
            selectedDate = date
        )
    }
}