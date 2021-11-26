package com.educacionit.libros

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.educacionit.retrofit.ApiLibros
import com.educacionit.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class LibrosFavoritosActivity : AppCompatActivity(){
    private lateinit var toolbar : Toolbar
    private lateinit var progressBar : ProgressBar
    private lateinit var rvLibrosFavoritos: RecyclerView
    private var adapter: LibrosAdapter = LibrosAdapter {
        Toast.makeText(this@LibrosFavoritosActivity, it.nombre, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libros_favoritos)

        setupUI()
        obtenerLibrosFavoritos()
    }

    private fun setupUI() {
        setupToolbar()
        setupProgressBar()
        setupAdapter()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Libros favoritos"
    }

    private fun setupProgressBar() {
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupAdapter() {
        rvLibrosFavoritos = findViewById(R.id.rvLibrosFavoritos)
        rvLibrosFavoritos.adapter = adapter
    }

    private fun mostrarProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun ocultarProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun obtenerLibrosFavoritos() {
        mostrarProgressBar()
        lifecycleScope.launch(Dispatchers.IO) {
            mostrarProgressBar()
            try {
                val libros = RetrofitClient.librosApi.getLibrosFavoritos().map {it.toLibro()}
                withContext(Dispatchers.Main) {
                    adapter.libros = libros
                }
            } catch (e : Exception) {
                runOnUiThread {
//                    mostrarMensaje(e.message ?: "No se pudieron obtener los libros favoritos")
                    mostrarMensaje( "No se pudieron obtener los libros favoritos")
                }

                e.printStackTrace()
            }finally {
                withContext(Dispatchers.Main) {
                    ocultarProgressBar()
                }
            }
        }
    }
}