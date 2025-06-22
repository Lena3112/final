package com.sena.afinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.sena.afinal.data.viewmodel.PeliculaViewModel
import com.sena.afinal.data.local.PeliculaEntity
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults

@Composable
fun DetalleScreen(nav: NavHostController, vm: PeliculaViewModel) {
    val scope = rememberCoroutineScope()
    val lista by vm.peliculas.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("🎞️ Películas Registradas", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(lista) { pelicula ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(pelicula.poster),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text("🎬 ${pelicula.titulo}", fontSize = 18.sp)
                        Text("📅 Año: ${pelicula.anio}")
                        Text("🎬 Director: ${pelicula.director}")
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Button(
                            onClick = { nav.navigate("registro/${pelicula.id}") },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("✏️")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                scope.launch {
                                    vm.eliminar(pelicula)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("🗑️")
                        }
                    }
                }
            }
        }
    }
}
