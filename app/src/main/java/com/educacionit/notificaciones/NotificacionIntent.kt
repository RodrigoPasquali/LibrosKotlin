package com.educacionit.notificaciones

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.educacionit.libros.HomeActivity
import com.educacionit.libros.R

class NotificacionIntent {
    private val CHANNEL_ID1 : String = "simple"

    fun lanzarNotificacion(context : Context, titulo : String, descripcion : String) {
        //Creacion de canal
//        crearCanal("Simple", "Notificacion simple", "Mensaje simple", context)
        crearCanal("Intent", "Prioritario con intent", "Mensaje prioritario", context)

        //Creacion de notificacion
//        val notificacionSimple : Notification = crearNotificacionSimple("Simple", context)
        val notificacionIntent : Notification =
                            crearNotificacionIntent("Intent", context, titulo, descripcion)

        //Lanzamiento de notificacion
        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(1, notificacionSimple)
        notificationManager.notify(2, notificacionIntent)
    }

    private fun crearNotificacionIntent(idNotificacion: String,
                                        context : Context,
                                        titulo : String,
                                        descripcion : String) : Notification {
        //Crear intent  que llame al main activity
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        //Se convierte notificacion en pending
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        //Se crea notificacion
        return NotificationCompat.Builder(context, idNotificacion)
            .setSmallIcon(R.drawable.ic_notificacion)
            .setContentTitle(titulo)
            .setContentText(descripcion)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
    }

//    private fun crearNotificacionSimple(idNotificacion: String, context: Context): Notification {
//        return NotificationCompat.Builder(context, idNotificacion)
//            .setSmallIcon(R.drawable.ic_notificacion)
//            .setContentTitle("titulo de mi notificacion")
//            .setContentText("texto de mi notificacion")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .build()
//    }

    private fun crearCanal(idInicial: String, nombre: String, descripcion: String, context: Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val miCanal = NotificationChannel(idInicial, nombre, importancia)
            miCanal.description = descripcion

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(miCanal)
        }
    }
}