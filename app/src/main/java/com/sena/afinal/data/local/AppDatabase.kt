package com.sena.afinal.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PeliculaEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peliculaDao(): PeliculaDao
    companion object {
        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "peliculas_db").build()
    }
}