package com.sena.afinal.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sena.afinal.data.local.PeliculaEntity
import com.sena.afinal.data.remote.PeliculaDTO
import com.sena.afinal.data.viewmodel.PeliculaViewModel

@Composable
fun RegistroScreen(nav: NavHostController, vm: PeliculaViewModel, id: Int?) {
    var titulo by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var poster by remember { mutableStateOf("") }

    var resultado by remember { mutableStateOf<PeliculaDTO?>(null) }
    val contexto = LocalContext.current
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(id) {
        if (id != null) {
            val peli = vm.cargarDetalle(id)
            peli?.let {
                titulo = it.titulo
                anio = it.anio
                director = it.director
                poster = it.poster
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (id == null) "Registro de Películas" else "Editar Película",
                fontSize = 24.sp
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") }
            )

            if (id == null) {
                Spacer(Modifier.height(8.dp))
                Button(onClick = {
                    vm.buscarApi(titulo.trim()) { dto ->
                        if (dto == null || dto.Response == "False") {
                            errorMensaje = "❌ Película no encontrada o error"
                            resultado = null
                        } else {
                            resultado = dto
                            errorMensaje = null
                            // llenar campos desde API
                            titulo = dto.Title ?: ""
                            anio = dto.Year ?: ""
                            director = dto.Director ?: ""
                            poster = dto.Poster ?: ""
                        }
                    }
                }) {
                    Text("Buscar en la API")
                }
            }

            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = anio, onValueChange = { anio = it }, label = { Text("Año") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = director, onValueChange = { director = it }, label = { Text("Director") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = poster, onValueChange = { poster = it }, label = { Text("Poster URL") })

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                val pelicula = PeliculaEntity(
                    id = id ?: 0,
                    titulo = titulo,
                    anio = anio,
                    director = director,
                    poster = poster
                )
                if (id == null) {
                    vm.insertar(pelicula) {
                        Toast.makeText(contexto, "Película guardada", Toast.LENGTH_SHORT).show()
                        nav.popBackStack()
                    }
                } else {
                    vm.actualizar(pelicula)
                    Toast.makeText(contexto, "Película actualizada", Toast.LENGTH_SHORT).show()
                    nav.popBackStack()
                }
            }) {
                Text(if (id == null) "Guardar en la App" else "Actualizar Película")
            }

            errorMensaje?.let {
                Spacer(Modifier.height(12.dp))
                Text(it)
            }
        }
    }
}
