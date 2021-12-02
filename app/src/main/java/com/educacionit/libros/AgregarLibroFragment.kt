package com.educacionit.libros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.notificaciones.NotificacionIntent
import com.squareup.picasso.Picasso

class AgregarLibroFragment : Fragment() {
    private lateinit var notificacion : NotificacionIntent
    private lateinit var etNombreLibro: EditText
    private lateinit var etAutor: EditText
    private lateinit var btnGuardar: Button
    private lateinit var imgLibro: ImageView
    private var urlImagen = "https://cdn.pixabay.com/photo/2018/01/03/09/09/book-3057904_1280.png"
    private lateinit var appContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpNotifications()
        appContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_libro, container, false);

        setupUI(view);

        return view;
    }

    private fun setupUI(view: View) {
        etNombreLibro = view.findViewById(R.id.etNombreLibro)
        etAutor = view.findViewById(R.id.etAutor)
        imgLibro = view.findViewById(R.id.imagenLibro)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener { guardarLibro() }

        descargarImagen(urlImagen)
    }

    private fun setUpNotifications() {
        notificacion = NotificacionIntent()
    }

    private fun guardarLibro() {
        if (datosValidos()) {
            val libro = Libro()
            libro.nombre = etNombreLibro.text.toString()
            libro.autor = etAutor.text.toString()
            guardarLibro(libro)
            requireActivity().setResult(AppCompatActivity.RESULT_OK, Intent().putExtra(HomeActivity.LIBRO, true))
            requireActivity().finish()

            notificacion.lanzarNotificacion(appContext, "Libro agregado", "Se agrego el libro ${libro.nombre}")
        } else {
            Toast.makeText(
                requireContext(),
                "Completar todos los campos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun guardarLibro(libro: Libro) {
        LibrosRepository(requireContext()).agregarLibro(libro)
    }

    private fun datosValidos(): Boolean {
        var datosValidos = true

        if (etNombreLibro.text.toString().isEmpty() || etAutor.text.toString().isEmpty()) {
            datosValidos = false
        }

        return datosValidos
    }

    private fun descargarImagen(url : String) {
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(url).error(R.drawable.splash_screen).into(imgLibro)
    }
}