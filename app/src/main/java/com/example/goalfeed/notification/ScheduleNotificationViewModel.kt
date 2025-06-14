package com.example.goalfeed.notification

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ScheduleNotificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    fun notifyNow(message: String) {
        println("DEBUG Notificando: $message") // o Log.d("NotiDebug", "Notificando...")
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("customMessage", message)
        context.sendBroadcast(intent)
    }

}
