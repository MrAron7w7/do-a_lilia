package com.example.dona_lilia.shared.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dona_lilia.shared.components.CustomTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CusttomLayout(
    modifier: Modifier = Modifier,
    title : String,
    content : @Composable () -> Unit,
) {
    Scaffold (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        topBar = { CustomTopBar( text = title ) },
        bottomBar = { CustomBottomBar() }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            content()
        }
    }
}