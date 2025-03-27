package com.example.goalfeed.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Rows() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("I am a normal row with 2 icons")
        Row(
            modifier = Modifier.background(color = Color.Blue.copy(alpha = 0.25f))
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
            Icon(
                Icons.Default.Star,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a row with 2 icons that fills the total width")
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue.copy(alpha = 0.25f))
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
            Icon(
                Icons.Default.Star,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a row with 2 icons that fills the total width and arranges items in the space available")
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue.copy(alpha = 0.25f)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "",
            )
            Icon(
                Icons.Default.Star,
                contentDescription = "",
            )
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a row with 3 icons that fills the total width and arranges items in the space available")
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue.copy(alpha = 0.25f)),
            horizontalArrangement = Arrangement.SpaceBetween,
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
        Spacer(Modifier.size(20.dp))

        Text("I am a row with a column and an icon inside")
        Row(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue.copy(alpha = 0.25f)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Title",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text("Subtitle")
            }
            Icon(Icons.Default.Add,
                contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewRows() {
    Rows()
}