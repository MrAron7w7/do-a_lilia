package com.example.dona_lilia.features.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dona_lilia.features.models.Products
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.shared.components.CustomLabel
import com.example.dona_lilia.shared.components.CusttomLayout
import com.example.dona_lilia.shared.theme.background
import com.example.dona_lilia.shared.theme.colorcard
import com.example.dona_lilia.shared.theme.primary
import com.example.dona_lilia.shared.theme.textColorUnselected
import java.text.SimpleDateFormat
import java.util.Locale

val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

var ListTickets = mutableListOf(
    Tickets(
        cod = "019701",
        clientName = "Pedro Yasuo Chavez Chamba",
        reference = "REF101",
        phoneNumber = "+51 936 548 401",
        ruc = "1234567891",
        destination = "Lima",
        deliveryDate = dateFormat.parse("2024-11-07")!!,
        products = listOf(
            Products("Producto A", 2, 15.0)
        ),
        totalAmount = 30.0,
        relevance = "verde"
    ),
    Tickets(
        cod = "019702",
        clientName = "Natalia Manchego Buenamarca",
        reference = "REF102",
        phoneNumber = "+51 936 548 402",
        ruc = "2234567892",
        destination = "Arequipa",
        deliveryDate = dateFormat.parse("2024-11-08")!!,
        products = listOf(
            Products("Producto B", 3, 12.0)
        ),
        totalAmount = 36.0,
        relevance = "amarillo"
    ),
    Tickets(
        cod = "019703",
        clientName = "Aron Magallanes",
        reference = "REF103",
        phoneNumber = "+51 936 548 403",
        ruc = "3234567893",
        destination = "Cusco",
        deliveryDate = dateFormat.parse("2024-11-09")!!,
        products = listOf(
            Products("Producto C", 1, 25.0)
        ),
        totalAmount = 25.0,
        relevance = "rojo"
    ),
    Tickets(
        cod = "019704",
        clientName = "Valeria Nicole Pachas Quintanilla",
        reference = "REF104",
        phoneNumber = "+51 936 548 404",
        ruc = "4234567894",
        destination = "Trujillo",
        deliveryDate = dateFormat.parse("2024-11-10")!!,
        products = listOf(
            Products("Producto D", 5, 10.0)
        ),
        totalAmount = 50.0,
        relevance = "verde"
    ),
    Tickets(
        cod = "019704",
        clientName = "Valeria Nicole Pachas Quintanilla",
        reference = "REF104",
        phoneNumber = "+51 936 548 404",
        ruc = "4234567894",
        destination = "Trujillo",
        deliveryDate = dateFormat.parse("2024-11-10")!!,
        products = listOf(
            Products("Producto D", 5, 10.0)
        ),
        totalAmount = 50.0,
        relevance = "verde"
    ),
    Tickets(
        cod = "019704",
        clientName = "Valeria Nicole Pachas Quintanilla",
        reference = "REF104",
        phoneNumber = "+51 936 548 404",
        ruc = "4234567894",
        destination = "Trujillo",
        deliveryDate = dateFormat.parse("2024-11-10")!!,
        products = listOf(
            Products("Producto D", 5, 10.0)
        ),
        totalAmount = 50.0,
        relevance = "verde"
    )
)

@Composable
fun TicketList(
    navController: NavController
) {
    CusttomLayout(
        title = "LISTA DE BOLETAS"
    ) {
        val context = LocalContext.current
        var filterByDate by remember { mutableStateOf(true) }

        // Ordenamos la lista en función del filtro actual
        val sortedOrders = remember(filterByDate) {
            if (filterByDate) {
                ListTickets.sortedBy { it.deliveryDate }
            } else {
                ListTickets.sortedBy {
                    when (it.relevance) {
                        "rojo" -> 1
                        "amarillo" -> 2
                        "verde" -> 3
                        else -> 4
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    onClick = { filterByDate = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Text(
                        text = "ORDEN POR FECHA",
                        color = if (filterByDate) primary else textColorUnselected,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        textDecoration = if (filterByDate) TextDecoration.Underline else TextDecoration.None
                    )
                }

                TextButton(
                    onClick = { filterByDate = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Text(
                        text = "ORDEN POR RELEVANCIA",
                        color = if (!filterByDate) primary else textColorUnselected,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        textDecoration = if (!filterByDate) TextDecoration.Underline else TextDecoration.None
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(sortedOrders) { tickets ->
                    OrderTicket(tickets)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Contabilizar", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(primary),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("CONTABILIZAR", fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(primary),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("IMPRIMIR PDF", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun OrderTicket(tickets: Tickets) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = colorcard)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 5.dp),

                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    tickets.clientName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = primary
                )
                Text(
                    "Fecha: ${dateFormat.format(tickets.deliveryDate)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    tickets.phoneNumber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "N. ${tickets.cod}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

