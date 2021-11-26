package com.educacionit.retrofit

import com.educacionit.libros.LibroResponse
import retrofit2.http.GET

interface ApiLibros {
    @GET("librosFavoritos")
    suspend fun getLibrosFavoritos(): List<LibroResponse>
}