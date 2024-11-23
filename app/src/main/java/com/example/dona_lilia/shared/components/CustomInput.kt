package com.example.dona_lilia.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomInput(
    value: String,
    label: String? = null,
    imageVector: ImageVector? = null,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
  OutlinedTextField(
      leadingIcon = {
          imageVector?.let {  // Solo mostrar el ícono si no es nulo
              Icon(imageVector = it, contentDescription = null)
          }
      },
      value = value,
      label = {
          label?.let {  // Solo mostrar el label si no es nulo
              Text(text = it)
          }
      },
      onValueChange = onValueChange,
      //modifier = Modifier.height(45.dp),
      keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Text
      ),
      shape = RoundedCornerShape(10.dp),
      colors = TextFieldDefaults.colors(
          //focusedTextColor = MaterialTheme.colorScheme.primary,
          //unfocusedTextColor = MaterialTheme.colorScheme.primary,
          disabledTextColor = Color.Red,
          focusedContainerColor = Color.White,
          unfocusedContainerColor = Color.White,
          cursorColor = Color.DarkGray,
      ),


  )
}