package com.example.dona_lilia.features.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.dona_lilia.shared.components.CusttomLayout

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import androidx.core.content.FileProvider
import com.example.dona_lilia.features.models.Users
import java.io.File
import java.io.FileOutputStream

var ListUsers = mutableListOf<Users>()

@Composable
fun TicketDetails (
    navController : NavController
) {
    val context = LocalContext.current  // Obtén el contexto aquí

    CusttomLayout(
        title = "DETALLES DE BOLETA"
    ) {
        Scaffold { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card para mostrar la información principal
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(Color(0xFF0301982), shape = RoundedCornerShape(40.dp))
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Información de RUC y usuario
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = null,
                                Modifier.size(70.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(
                                    text = "RUC",
                                    style = TextStyle(Color(0xff031982), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "12222222",
                                    style = TextStyle(fontWeight = FontWeight.Bold)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Información adicional
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = "Camila", style = TextStyle(fontWeight = FontWeight.Bold)) // Firstname
                                Text(text = "Fernandez dias", style = TextStyle(fontWeight = FontWeight.Bold)) // Lastname
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = "Celular", style = TextStyle(fontWeight = FontWeight.Bold)) // phone
                            }
                            Text(text = "Fecha:", style = TextStyle(fontWeight = FontWeight.Bold)) // Date
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // LazyColumn para los productos
                        LazyColumn {
                            ListUsers.add(Users("Camilo", "1234567890", "987654324", "aaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaa"))
                            ListUsers.add(Users("Camilo", "1234567890", "987654324", "aaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaa"))

                            itemsIndexed(ListUsers) { index, item ->
                                Card(
                                    Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(Color.White),
                                ) {
                                    Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.AccountBox,
                                            contentDescription = null,
                                            Modifier.size(50.dp)
                                        )
                                        Spacer(modifier = Modifier.width(15.dp))
                                        Column {
                                            Text(text = "Nombre: ${item.name}", style = TextStyle(fontWeight = FontWeight.Bold))
                                            Text(text = "Cantidad: ${item.address}", style = TextStyle(fontWeight = FontWeight.Bold))
                                            Text(text = "Precio: ${item.ruc}", style = TextStyle(fontWeight = FontWeight.Bold))
                                        }
                                    }
                                }
                                HorizontalDivider(modifier = Modifier.padding(2.dp))
                            }
                        }
                    }
                }

                // Botón para descargar el PDF
                Button(
                    onClick = { generatePdf(context) }, // Pasa el contexto correcto
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xff031982)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Descargar PDF")
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botón para regresar al inicio
                Button(
                    onClick = { /*TODO*/ },
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xff031982)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Inicio")
                }
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
