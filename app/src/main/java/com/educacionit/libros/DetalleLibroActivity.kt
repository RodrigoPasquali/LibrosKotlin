package com.educacionit.libros

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleLibroActivity : AppCompatActivity() {
    private lateinit var tvLibro: TextView
    private lateinit var tvAutor: TextView
    private var libro: Libro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_libro)

        libro = intent.getSerializableExtra(HomeActivity.LIBRO) as Libro

        setupUI()
        initTextViews()
    }

    private fun setupUI() {
        tvLibro = findViewById(R.id.tvLibro)
        tvAutor = findViewById(R.id.tvAutor)
    }

    private fun initTextViews() {
        libro?.let {
            tvLibro.text = it.nombre
            tvAutor.text = it.autor
        }
    }
}