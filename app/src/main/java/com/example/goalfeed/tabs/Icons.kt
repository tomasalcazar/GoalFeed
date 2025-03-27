package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Icons() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("I am a normal icon")
        Icon(
            Icons.Default.Favorite,
            contentDescription = "",
        )
        Spacer(Modifier.size(20.dp))

        Text("I am a large icon")
        Icon(
            Icons.Default.Favorite,
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )
        Spacer(Modifier.size(20.dp))

        Text("I am a colored icon")
        Icon(
            Icons.Default.Favorite,
            contentDescription = "",
            tint = Color.Cyan,
        )
        Spacer(Modifier.size(20.dp))
    }
}

@Preview
@Composable
fun PreviewIcons() {
    Icons()
}