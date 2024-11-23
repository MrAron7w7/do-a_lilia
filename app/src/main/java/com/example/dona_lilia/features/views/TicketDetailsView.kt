package com.example.dona_lilia.features.views

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.core.content.FileProvider
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.shared.components.CusttomLayout
import com.example.dona_lilia.shared.components.ProductItem
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TicketDetails(
    ticket: Tickets,
    navController: NavController
) {
    CusttomLayout(title = "Detalles de Boleta") {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Información del cliente
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            tint = Color.Black,
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Column {
                            Text(
                                "N°: ${ticket.cod}",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                            Text("RUC: ${ticket.ruc}", style = TextStyle(color = Color.Black))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Cliente: ${ticket.clientName}",
                                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                            )
                            Text(ticket.phoneNumber, style = TextStyle(color = Color.Black))
                            Text(ticket.destination, style = TextStyle(color = Color.Black))
                        }
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        Text(
                            "Fecha de Entrega: ${dateFormat.format(ticket.deliveryDate)}",
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Productos Seleccionados",
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "Cantidad: ${ticket.products.size} productos",
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold
                        )
                    )

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(ticket.products) { product ->
                            ProductItem(product = product)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { generatePdf(context, ticket) },
                    Modifier

                        .padding(horizontal = 15.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xff031982)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Descargar PDF")
                }
                Button(
                    onClick = { navController.popBackStack() },
                    Modifier

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
}

@RequiresApi(Build.VERSION_CODES.Q)
fun generatePdf(context: Context, ticket: Tickets) {
    try {
        val fileName = "ticket_${ticket.cod}.pdf"
        val outputStream = createPdfWithMediaStore(context, fileName)
            ?: throw Exception("No se pudo crear el archivo en MediaStore")

        val pdfWriter = PdfWriter(outputStream)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument, PageSize.A5)

        // Definir colores
        val primaryColor = ColorConstants.DARK_GRAY
        val secondaryColor = ColorConstants.GRAY
        val accentColor = DeviceRgb(0, 102, 204)

        // Formatear la fecha
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(ticket.deliveryDate)

        // Agregar logo o nombre de la empresa
        val header = Table(UnitValue.createPercentArray(1)).useAllAvailableWidth()
        header.addCell(
            Cell().add(
                Paragraph("DOÑA LILIA")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20f)
                    .setFontColor(primaryColor)
                    .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        document.add(header)

        // Información de la empresa
        val companyInfo = Table(UnitValue.createPercentArray(1)).useAllAvailableWidth()
        companyInfo.addCell(
            Cell().add(
                Paragraph("RUC: 20494645760\n" +
                        "Correo: contacto.disvica@gmail.com\n" +
                        "Teléfono: 995 018 533")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10f)
                    .setFontColor(secondaryColor)
            ).setBorder(Border.NO_BORDER)
        )
        document.add(companyInfo)

        // Título de la boleta
        document.add(
            Paragraph("BOLETA DE VENTA ELECTRÓNICA")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(14f)
                .setFontColor(accentColor)
                .setBold()
                .setMarginTop(20f)
        )

        // Número de boleta
        document.add(
            Paragraph("N° ${ticket.cod}")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12f)
                .setFontColor(primaryColor)
        )

        // Información del cliente
        val clientInfo = Table(UnitValue.createPercentArray(2)).useAllAvailableWidth()
        clientInfo.setMarginTop(20f)

        // Primera columna
        clientInfo.addCell(
            Cell().add(Paragraph("CLIENTE:")
                .setFontColor(primaryColor)
                .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        clientInfo.addCell(
            Cell().add(Paragraph(ticket.clientName)
                .setFontColor(secondaryColor)
            ).setBorder(Border.NO_BORDER)
        )

        clientInfo.addCell(
            Cell().add(Paragraph("TELÉFONO:")
                .setFontColor(primaryColor)
                .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        clientInfo.addCell(
            Cell().add(Paragraph(ticket.phoneNumber)
                .setFontColor(secondaryColor)
            ).setBorder(Border.NO_BORDER)
        )

        clientInfo.addCell(
            Cell().add(Paragraph("DESTINO:")
                .setFontColor(primaryColor)
                .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        clientInfo.addCell(
            Cell().add(Paragraph(ticket.destination)
                .setFontColor(secondaryColor)
            ).setBorder(Border.NO_BORDER)
        )

        clientInfo.addCell(
            Cell().add(Paragraph("FECHA DE ENTREGA:")
                .setFontColor(primaryColor)
                .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        clientInfo.addCell(
            Cell().add(Paragraph(formattedDate)
                .setFontColor(secondaryColor)
            ).setBorder(Border.NO_BORDER)
        )

        document.add(clientInfo)

        // Tabla de productos
        val productTable = Table(UnitValue.createPercentArray(floatArrayOf(40f, 20f, 20f, 20f)))
            .useAllAvailableWidth()
            .setMarginTop(20f)

        // Encabezados de la tabla
        arrayOf("Producto", "Cantidad", "Precio Unit.", "Subtotal").forEach { header ->
            productTable.addHeaderCell(
                Cell().add(
                    Paragraph(header)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontColor(ColorConstants.WHITE)
                        .setBold()
                ).setBackgroundColor(accentColor)
            )
        }

        // Contenido de la tabla
        var total : Double = 0.0
        ticket.products.forEach { product ->
            val subtotal = product.quantity * product.unitPrice
            total += subtotal

            productTable.addCell(Cell().add(Paragraph(product.productName)))
            productTable.addCell(Cell().add(Paragraph(product.quantity.toString())).setTextAlignment(TextAlignment.CENTER))
            productTable.addCell(Cell().add(Paragraph("S/ ${String.format("%.2f", product.unitPrice)}")).setTextAlignment(TextAlignment.RIGHT))
            productTable.addCell(Cell().add(Paragraph("S/ ${String.format("%.2f", subtotal)}")).setTextAlignment(TextAlignment.RIGHT))
        }

        document.add(productTable)

        // Total
        val totalTable = Table(UnitValue.createPercentArray(floatArrayOf(80f, 20f)))
            .useAllAvailableWidth()
            .setMarginTop(10f)

        totalTable.addCell(
            Cell().add(
                Paragraph("TOTAL:")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontColor(primaryColor)
                    .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        totalTable.addCell(
            Cell().add(
                Paragraph("S/ ${String.format("%.2f", total)}")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontColor(primaryColor)
                    .setBold()
            ).setBorder(Border.NO_BORDER)
        )
        document.add(totalTable)

        // Pie de página
        val footer = Table(UnitValue.createPercentArray(1)).useAllAvailableWidth()
        footer.setMarginTop(30f)
        footer.addCell(
            Cell().add(
                Paragraph("¡Gracias por su compra!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(accentColor)
                    .setItalic()
            ).setBorder(Border.NO_BORDER)
        )
        document.add(footer)

        document.close()

        Toast.makeText(context, "PDF guardado correctamente en Descargas", Toast.LENGTH_LONG).show()

    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error al generar el PDF: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createPdfWithMediaStore(context: Context, fileName: String): OutputStream? {
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.MediaColumns.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}")
        }
    }
    val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
    return uri?.let { resolver.openOutputStream(it) }
}
