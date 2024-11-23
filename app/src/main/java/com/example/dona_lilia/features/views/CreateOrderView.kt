package com.example.dona_lilia.features.views


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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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

    // Estados para los datos del cliente
    var name = remember { mutableStateOf("") }
    var reference = remember { mutableStateOf("") }
    var phoneNumber = remember { mutableStateOf("") }
    var ruc = remember { mutableStateOf("") }
    var destination = remember { mutableStateOf("") }
    var date = remember { mutableStateOf("") }
    var context = LocalContext.current
    val db = FirebaseService()
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
                CustomerInputFields(
                    name = name,
                    reference = reference,
                    phoneNumber = phoneNumber,
                    ruc = ruc,
                    destination = destination,
                    date = date
                )

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
                    val product = Products(
                        productName = "Producto A",
                        quantity = 10,
                        unitPrice = 15.5
                    )
                    val ticket = Tickets(
                        cod = "T12345",
                        clientName = "Cliente A",
                        reference = "Referencia",
                        phoneNumber = "987654321",
                        ruc = "12345678901",
                        destination = "Destino A",
                        deliveryDate = Date(),
                        products = listOf(
                            Products("Producto A", 2, 15.0)
                        ), // Productos seleccionados
                        totalAmount = 155.0,
                        relevance = "Alta"
                    )


                    val user = Users(
                        name = name.value,
                        ruc = ruc.value,
                        phone = phoneNumber.value,
                        address = destination.value,
                        referece = reference.value,
                        destine = destination.value,
                        date = date.value
                    )
                    if (name.value.isBlank() || phoneNumber.value.isBlank()) {
                        Toast.makeText(context, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                    } else {
                        name.value = ""
                        reference.value = ""
                        phoneNumber.value = ""
                        destination.value = ""
                        date.value = ""
                        ruc.value = ""
                        Toast.makeText(context, "Usuario agregado con éxito", Toast.LENGTH_LONG).show()
                        db.addUser(user)

                    }

                    //db.addUser(user)
                    db.addTicket(ticket)
                    db.addProduct(product)

                }

                CustomButton(text = "ATRAS") {
                    navController.popBackStack()
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
    Column {
        // Nombre
        CustomInput(
            label = "Nombre",
            value = name.value,
            imageVector = Icons.Default.Person,
        ) { name.value = it }
        Spacer(modifier = Modifier.height(10.dp))

        // Referencia
        CustomInput(
            label = "Referencia",
            value = reference.value,
            imageVector = Icons.Filled.Bookmark,
        ) { reference.value = it }
        Spacer(modifier = Modifier.height(10.dp))

        // Teléfono
        CustomInput(
            label = "Numero",
            value = phoneNumber.value,
            imageVector = Icons.Default.Phone,
        ) { phoneNumber.value = it }
        Spacer(modifier = Modifier.height(10.dp))

        // R.U.C.
        CustomInput(
            label = "R.U.C",
            value = ruc.value,
            imageVector = Icons.Filled.Numbers,
        ) { ruc.value = it }
        Spacer(modifier = Modifier.height(10.dp))

        // Destino
        CustomInput(
            label = "Destino",
            value = destination.value,
            imageVector = Icons.Default.Place,
        ) { destination.value = it }
        Spacer(modifier = Modifier.height(10.dp))

        // Fecha
        DatePickerField(label = "Fecha", selectedDate = date)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(label: String, selectedDate: MutableState<String>) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Listener para seleccionar la fecha
    val datePicker = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formattedDate = String.format("%02d-%02d-%d", dayOfMonth, month + 1, year)
            selectedDate.value = formattedDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Restringir el DatePicker para fechas a partir de hoy
    datePicker.datePicker.minDate = System.currentTimeMillis()

    // Campo de texto para abrir el DatePicker
    Box(
        modifier = Modifier

            .width(280.dp)
            .clickable { datePicker.show() }
            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .background(Color.White),

    ) {

        Row (
            modifier = Modifier
                .padding(15.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = if (selectedDate.value.isEmpty()) label else selectedDate.value,
                color = if (selectedDate.value.isEmpty()) Color.Black else Color.Black,
                fontSize = 16.sp
            )
        }
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
                            selectedOption.value = selectedOption.value
                                .toMutableMap()
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
