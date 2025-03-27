package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Cards() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Card {
            Text(
                "I am a normal card"
            )
        }
        Card(shape = RoundedCornerShape(20.dp)) {
            Text(
                "I am a card with rounded corners and padding",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
        Card(shape = RoundedCornerShape(50.dp)) {
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp)) {
                Text(
                    "I am a card with rows and columns inside"
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "",
                    )
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "",
                    )
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "",
                    )
                }
            }
        }
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)) {
            Text(
                "I am a card with elevation",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
        Card(onClick = {}, enabled = false) {
            Text(
                "I am a card disabled",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCards() {
    Cards()
}