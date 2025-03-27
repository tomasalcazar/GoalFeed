package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FABs() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("I am a FAB")
        FloatingActionButton(
            onClick = {},
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a FAB with a circle shape")
        FloatingActionButton(
            onClick = {},
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a FAB with a colored background")
        FloatingActionButton(
            onClick = {},
            containerColor = Color.Cyan
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))
    }
}

@Preview
@Composable
fun PreviewFABs() {
    FABs()
}