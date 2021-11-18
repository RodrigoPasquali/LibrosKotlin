package com.educacionit.libros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AgregarLibroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_libro)

        agregarFragmento();
    }

    private fun agregarFragmento() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.agregarLibroFragment, AgregarLibroFragment(), "AgregarLibroFragment")
            .commit()
//            .add(R.id.container, AgregarLibroFragment(), AgregarLibroFragment::javaClass.name)
    }
}