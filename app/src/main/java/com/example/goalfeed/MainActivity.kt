package com.example.goalfeed

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.goalfeed.navigation.BottomBar
import com.example.goalfeed.navigation.NavHostComposable
import com.example.goalfeed.ui.theme.GoalFeedTheme
import androidx.fragment.app.FragmentActivity
import com.example.goalfeed.notification.notificationChannelID
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.FirebaseApp

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        createNotificationChannel()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GoalFeedTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(navController::navigate)
                    }
                ) { innerPadding ->
                    NavHostComposable(innerPadding, navController)
                }
            }
        }
    }

    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            notificationChannelID,
            "Learning Android Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}
