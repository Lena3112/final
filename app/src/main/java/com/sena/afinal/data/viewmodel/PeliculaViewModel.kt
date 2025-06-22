package com.sena.afinal.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sena.afinal.data.local.AppDatabase
import com.sena.afinal.data.local.PeliculaEntity
import com.sena.afinal.data.remote.PeliculaDTO
import com.sena.afinal.data.remote.RetrofitInstance
import com.sena.afinal.data.repository.PeliculaRepository
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PeliculaViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).peliculaDao()
    private val repo = PeliculaRepository(dao)
    val peliculas = repo.obtenerTodos().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _detalle = MutableStateFlow<PeliculaEntity?>(null)
    val detalle: StateFlow<PeliculaEntity?> = _detalle

    suspend fun cargarDetalle(id: Int): PeliculaEntity? {
        val resultado = repo.obtenerPorId(id)
        _detalle.value = resultado
        return resultado
    }

    fun insertar(p: PeliculaEntity, onResult: (Int) -> Unit) = viewModelScope.launch {
        val id = repo.insertar(p).toInt()
        onResult(id)
    }
    fun actualizar(p: PeliculaEntity) = viewModelScope.launch { repo.actualizar(p) }
    fun eliminar(p: PeliculaEntity) = viewModelScope.launch { repo.eliminar(p) }

    fun buscarApi(titulo: String, onResult: (PeliculaDTO?) -> Unit) {
        viewModelScope.launch {
            try {
                val resultado = RetrofitInstance.api.buscarPelicula(titulo)
                if (resultado.Response == "True") {
                    onResult(resultado)
                } else {
                    // La API respondió, pero no encontró la película
                    Log.e("API_ERROR", resultado.Error ?: "Error desconocido")
                    onResult(null)
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", "Error al buscar película", e)
                onResult(null)

            }
        }
    }
}
