package com.sena.afinal.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {
    @Query("SELECT * FROM PeliculaEntity")
    fun obtenerTodas(): kotlinx.coroutines.flow.Flow<List<PeliculaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(p: PeliculaEntity): Long

    @Update
    suspend fun actualizar(pelicula: PeliculaEntity)

    @Delete
    suspend fun eliminar(pelicula: PeliculaEntity)

    @Query("SELECT * FROM PeliculaEntity WHERE id = :id")
    suspend fun obtenerPorId(id: Int): PeliculaEntity?
}