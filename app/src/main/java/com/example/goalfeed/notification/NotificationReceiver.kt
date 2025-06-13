package com.example.goalfeed.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.goalfeed.R
import kotlin.random.Random

const val notificationChannelID = "learning_android_notification_channel"

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        println("DEBUG Llego onReceive NotificationReceiver!")
        createNotificationChannel(context)

        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("Test")
            .setContentText("Funciona la notificación")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(Random.nextInt(), notification)
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            notificationChannelID,
            "General Notifications",
            NotificationManager.IMPORTANCE_HIGH // 👈 Esto es CLAVE
        ).apply {
            description = "Notificaciones de GoalFeed"
            setShowBadge(true)
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}
