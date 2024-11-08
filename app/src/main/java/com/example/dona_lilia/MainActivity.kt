package com.example.dona_lilia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dona_lilia.core.route.RouteView
import com.example.dona_lilia.shared.theme.Dona_liliaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dona_liliaTheme (darkTheme = false) {
                RouteView()
            }
        }
    }
}
