package com.sena.afinal.data.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sena.afinal.data.viewmodel.PeliculaViewModel
import com.sena.afinal.ui.screens.DetalleScreen
import com.sena.afinal.ui.screens.HomeScreen
import com.sena.afinal.ui.screens.RegistroScreen


// Navigation.kt
sealed class Screens(val ruta: String) {
    object Home : Screens("home")
    object Registro : Screens("registro/{id}") {
        fun crearRuta(id: Int?) = "registro/${id ?: -1}"
    }
    object Detalle : Screens("detalle") {
        fun crearRuta() = "detalle"
    }
}

@Composable
fun NavGraph(nav: NavHostController, vm: PeliculaViewModel, padding: PaddingValues) {
    NavHost(navController = nav, startDestination = Screens.Home.ruta) {

        composable(Screens.Home.ruta) {
            HomeScreen(nav, vm)
        }

        composable(
            route = Screens.Registro.ruta,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")?.toIntOrNull()
            RegistroScreen(nav, vm, if (id == -1) null else id)
        }

        composable(Screens.Detalle.ruta) {
            DetalleScreen(nav, vm)
        }
    }
}
