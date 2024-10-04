package com.example.dona_lilia.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dona_lilia.R
import java.util.Date

@Composable
fun CustomCard (
    name: String,
    date: Date,
    number : String,
    ticket: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier

            .padding(horizontal = 20.dp)

            .clip(RoundedCornerShape(25.dp))

            .fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor = MaterialTheme.colorScheme.tertiary

        ),
        onClick = { onClick() }

    ) {
        Column (

            modifier = Modifier

                .fillMaxWidth()

                .padding(20.dp),

            ) {
            Row (
                modifier = Modifier

                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically

            ) {
                // Imagen
                Image(

                    painter = painterResource(id = R.drawable.box), // Cambia 'logo' por el nombre de tu imagen

                    contentDescription = null,

                    modifier = Modifier

                        .size(60.dp) // Ajusta el tamaño según sea necesario

                        .clip(RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.Crop // Ajusta la escala de la imagen
                )

                Spacer(modifier = Modifier.width(20.dp))


                // Destalles de la boleta
                Column (

                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    CustomLabel(

                        text = name,

                        fontWeight = FontWeight.W600,

                        fontSize = 22.sp,

                        color = MaterialTheme.colorScheme.primary,

                        )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Fecha
                    CustomLabel(

                        text = "Fecha: $date",

                        fontWeight = FontWeight.Bold,

                        fontSize = 14.sp,

                        color = Color.Black,

                        )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Numero
                    CustomLabel(

                        text = "Numero: $number",

                        fontWeight = FontWeight.Bold,

                        fontSize = 14.sp,

                        color = Color.Black,

                        )

                }

            }

            Spacer(modifier = Modifier.height(5.dp))

            // NUMERO DE BOLETA
            Box(

                modifier = Modifier

                    .fillMaxWidth(),



                contentAlignment = Alignment.CenterEnd

            ) {
                CustomLabel(

                    fontSize = 12.sp,

                    text = "N. $ticket",

                    fontWeight = FontWeight.W500,

                    color = Color.Black

                )
            }
        }

    }

}