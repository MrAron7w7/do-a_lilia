package com.example.dona_lilia.features.views

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dona_lilia.shared.components.CustomButton
import com.example.dona_lilia.shared.components.CustomInput
import com.example.dona_lilia.shared.components.CustomLabel
import com.example.dona_lilia.shared.components.CustomTextButton
import com.example.dona_lilia.shared.components.CusttomLayout

@Composable
fun CreateOrderView (
    navController : NavController
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

                // Inputs
                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                ) {

                    CustomLabel(text = "Nombre:")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    CustomLabel(text = "Referencia:")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    CustomLabel(text = "Telefono:")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    CustomLabel(text = "R.U.C:    ")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    CustomLabel(text = "Destino:")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    CustomLabel(text = "Fecha E:")

                    Spacer(modifier = Modifier.width(10.dp))

                    CustomInput()

                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomLabel(
                    text = "PRODUCTOS SOLICITADOS",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomLabel(

                    text = "RELEVANCIA",

                    fontSize = 17.sp

                )

                Spacer(modifier = Modifier.height(20.dp))

                Row (

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
                    
                }

                CustomButton(text = "ATRAS") {

                    navController.popBackStack()

                }
            }
        }
    }
}