package com.sena.afinal.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun buscarPelicula(
        @Query("t") titulo: String,
        @Query("apikey") apiKey: String = "89343b2"
    ): PeliculaDTO
}