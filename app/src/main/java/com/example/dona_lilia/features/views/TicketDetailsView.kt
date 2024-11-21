package com.example.dona_lilia.features.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.features.models.Users
import com.example.dona_lilia.shared.components.CusttomLayout
import com.example.dona_lilia.shared.components.ProductItem
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.File
import java.io.FileOutputStream

var ListUsers = mutableListOf<Users>()

@Composable
fun TicketDetails(
    ticket: Tickets,
    navController: NavController,
) {
    CusttomLayout(
        title = "Detalles de Boleta"
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Información del cliente
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Icon(
                            modifier = Modifier.size(70.dp),
                            tint = Color.Black,
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(){
                            Text("RUC: ${ticket.ruc}",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            )
                            Text("N°: ${ticket.cod}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                        ) {
                            Text(
                                text = "Cliente: ${ticket.clientName}",
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                            )
                            Text("${ticket.phoneNumber}")
                            Text("${ticket.destination}")
                        }
                        Text("Fecha de Entrega: ${dateFormat.format(ticket.deliveryDate)}",
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Productos Seleccionados",
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                    )

                    LazyColumn(
                        //modifier = Modifier.weight(1f)
                    ) {
                        items(ticket.products) { product ->
                            ProductItem(product = product)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { generatePdf(context) },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff031982)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Descargar PDF")
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Botón para regresar
            Button(
                onClick = { navController.popBackStack() },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff031982)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Regresar")
            }
        }
    }
}

fun generatePdf(context: Context) {
    try {
        // Obtener el siguiente nombre disponible para el archivo
        val fileName = getNextFileName()

        // Crear el archivo PDF en la carpeta de descargas con el nombre generado
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "$fileName.pdf")
        val fileOutputStream = FileOutputStream(file)

        // Crear un PdfWriter para escribir el PDF
        val pdfWriter = PdfWriter(fileOutputStream)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        // Configurar título
        document.add(Paragraph("Ticket Detail").setBold().setFontSize(18f))

        // Información de la boleta (RUC, nombre, celular, fecha)
        document.add(Paragraph("RUC: 12222222"))
        document.add(Paragraph("Nombre: Camila Fernandez Dias"))
        document.add(Paragraph("Celular: 1234567890"))
        document.add(Paragraph("Fecha: 2024-11-07"))

        // Añadir una línea de separación
        document.add(Paragraph("----------------------------------"))

        // Agregar los productos
        ListUsers.forEach { user ->
            document.add(Paragraph("Nombre: ${user.name}"))
            document.add(Paragraph("Cantidad: ${user.address}"))
            document.add(Paragraph("Precio: ${user.ruc}"))
            document.add(Paragraph("----------------------------------"))
        }

        // Cerrar el documento
        document.close()

        // Verificamos si el archivo existe y mostramos un mensaje
        if (file.exists()) {
            Toast.makeText(context, "PDF guardado en Descargas: ${file.absolutePath}", Toast.LENGTH_LONG).show()

            // Usar FileProvider para abrir el PDF
            val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY
            }
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "No se pudo crear el PDF", Toast.LENGTH_SHORT).show()
        }

    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error al crear el PDF", Toast.LENGTH_SHORT).show()
    }
}

@SuppressLint("DefaultLocale")
fun getNextFileName(): String {
    // Directorio de descargas
    val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    // Prefijo y contador inicial
    val prefix = "boleta_"
    var counter = 1

    // Incrementa el contador hasta encontrar un nombre de archivo no existente
    var fileName: String
    do {
        fileName = "$prefix${String.format("%03d", counter)}"
        counter++
    } while (File(downloadsDir, "$fileName.pdf").exists())

    return fileName // Retorna el nombre del archivo disponible
}


/*

fun generatePdf(context: Context) {
    try {
        // Obtener el siguiente nombre disponible para el archivo
        val fileName = getNextFileName()

        // Crear el archivo PDF en la carpeta de descargas con el nombre generado
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "$fileName.pdf")
        val fileOutputStream = FileOutputStream(file)

        // Crear un PdfWriter para escribir el PDF
        val pdfWriter = PdfWriter(fileOutputStream)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        // Estilos
        val titleStyle = Style().setFontSize(16f).setBold().setFontColor(ColorConstants.BLACK)
        val textStyle = Style().setFontSize(12f).setFontColor(ColorConstants.BLACK)
        val cardStyle = Style().setBorder(Border.NO_BORDER).setBackgroundColor(DeviceRgb(240, 240, 240)).setPadding(10f)

        // Configurar título
        document.add(Paragraph("Ticket Detail").setBold().setFontSize(18f))

        // Encabezado
        val headerTable = Table(2).setWidth(UnitValue.createPercentValue(100f))
        headerTable.addCell(Cell().add(Paragraph("R.U.C: ${ticket.ruc}").addStyle(titleStyle)).setBorder(Border.NO_BORDER))
        headerTable.addCell(Cell().add(Paragraph("N°: ${ticket.id}").addStyle(titleStyle).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER))
        headerTable.addCell(Cell().add(Paragraph("Cliente: ${ticket.clientName}").addStyle(textStyle)).setBorder(Border.NO_BORDER))
        headerTable.addCell(Cell().add(Paragraph("Fecha de Entrega: ${ticket.deliveryDate}").addStyle(textStyle).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER))
        headerTable.addCell(Cell().add(Paragraph("Teléfono: ${ticket.phoneNumber}").addStyle(textStyle)).setBorder(Border.NO_BORDER))
        headerTable.addCell(Cell().add(Paragraph("Destino: ${ticket.destination}").addStyle(textStyle).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER))
        document.add(headerTable)
        // Espaciado
        document.add(Paragraph("\n"))

        // Añadir una línea de separación
        document.add(Paragraph("----------------------------------"))
        // Espaciado
        document.add(Paragraph("\n"))

        // Agregar los productos
        ticket.products.forEach { product ->
            val productCard = Table(1).setWidth(UnitValue.createPercentValue(100f)).addStyle(cardStyle)
            productCard.addCell(Cell().add(Paragraph("Nombre: ${product.name}").addStyle(textStyle)).setBorder(Border.NO_BORDER))
            productCard.addCell(Cell().add(Paragraph("Cantidad: ${product.quantity}").addStyle(textStyle)).setBorder(Border.NO_BORDER))
            productCard.addCell(Cell().add(Paragraph("Precio: ${product.price}").addStyle(textStyle)).setBorder(Border.NO_BORDER))
            document.add(productCard)
            document.add(Paragraph("\n")) // Espaciado entre productos
        }

        // Cerrar el documento
        document.close()

        // Verificamos si el archivo existe y mostramos un mensaje
        if (file.exists()) {
            Toast.makeText(context, "PDF guardado en Descargas: ${file.absolutePath}", Toast.LENGTH_LONG).show()

            // Usar FileProvider para abrir el PDF
            val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY
            }
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "No se pudo crear el PDF", Toast.LENGTH_SHORT).show()
        }

    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error al crear el PDF", Toast.LENGTH_SHORT).show()
    }
}

@SuppressLint("DefaultLocale")
fun getNextFileName(): String {
    // Directorio de descargas
    val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    // Prefijo y contador inicial
    val prefix = "boleta_"
    var counter = 1

    // Incrementa el contador hasta encontrar un nombre de archivo no existente
    var fileName: String
    do {
        fileName = "$prefix${String.format("%03d", counter)}"
        counter++
    } while (File(downloadsDir, "$fileName.pdf").exists())

    return fileName // Retorna el nombre del archivo disponible
}




@Composable
fun TicketDetailsView(ticketId: String, navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var ticket by remember { mutableStateOf<Ticket?>(null) }
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }

    // Cargar los datos del ticket
    LaunchedEffect(ticketId) {
        db.collection("tickets")
            .document(ticketId)
            .get()
            .addOnSuccessListener { document ->
                ticket = document.toObject(Ticket::class.java)
            }

        // Cargar los productos del ticket (si están en una subcolección)
        db.collection("tickets")
            .document(ticketId)
            .collection("products")
            .get()
            .addOnSuccessListener { result ->
                products = result.map { it.toObject(Product::class.java) }
            }
    }

    // Para mostrar los datos
    if (ticket != null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Icon(
                            modifier = Modifier.size(70.dp),
                            tint = Color.Black,
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(){
                            Text("N°: ${ticket!!.id}",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            )
                            Text("RUC: ${ticket!!.ruc}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                        ) {
                            Text(
                                text = "${ticket!!.clientName}",
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                            )
                            Text("${ticket!!.phoneNumber}")
                            Text("${ticket!!.destination}")
                        }
                        Text("Fecha de Entrega: ${dateFormat.format(ticket!!.deliveryDate)}",
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // Mostrar la lista de productos
                    Text(
                        text = "Productos",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Total: S/.${ticket!!.totalAmount}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(products) { product ->
                            ProductItem(product)
                        }
                    }
                }
            }
        }
    } else {
        // Mostrar un indicador de carga mientras se obtienen los datos
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

*/
