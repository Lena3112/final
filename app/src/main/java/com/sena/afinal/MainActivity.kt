package com.sena.afinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sena.afinal.data.navigation.NavGraph
import com.sena.afinal.data.viewmodel.PeliculaViewModel
import com.sena.afinal.ui.theme.FinalTheme

class MainActivity : ComponentActivity() {
    private val viewModel: PeliculaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    NavGraph(nav = navController, vm = viewModel, padding = padding)
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        FinalTheme {
            Greeting("Android")
        }
    }
}