package com.example.dona_lilia.shared.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable

fun CustomLabel (

    modifier: Modifier = Modifier,

    text : String,

    fontSize :  TextUnit = 16.sp,

    fontWeight: FontWeight? = null,

    color : Color = Color.Unspecified,

    ) {
    Text(
        modifier = modifier,

        text =  text ,

        style = TextStyle (

            fontSize = fontSize,

            fontWeight = fontWeight,

            color = color,

            )
    )
}