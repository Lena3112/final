package com.sena.afinal.data.repository

import com.sena.afinal.data.local.PeliculaDao
import com.sena.afinal.data.local.PeliculaEntity
import kotlinx.coroutines.flow.Flow

class PeliculaRepository(private val dao: PeliculaDao) {
    fun obtenerTodos(): kotlinx.coroutines.flow.Flow<List<PeliculaEntity>> = dao.obtenerTodas()
    suspend fun obtenerPorId(id: Int): PeliculaEntity? = dao.obtenerPorId(id)
    suspend fun insertar(p: PeliculaEntity): Long = dao.insertar(p)
    suspend fun actualizar(p: PeliculaEntity) = dao.actualizar(p)
    suspend fun eliminar(p: PeliculaEntity) = dao.eliminar(p)
}