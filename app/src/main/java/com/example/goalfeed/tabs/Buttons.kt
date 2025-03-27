package com.example.goalfeed.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Buttons() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Button(
            onClick = {},
        ) {
            Text("I am a button")
        }
        Button(
            onClick = {},
        ) {
            Row {
                Text("I am a button with an icon")
                Spacer(Modifier.size(5.dp))
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "",
                )
            }
        }
        Button(
            onClick = {},
            enabled = false,
        ) {
            Text("I am a button disabled")
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
        ) {
            Text("I am a button with rounded corners")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Green,
            )
        ) {
            Text("I am a button with specified background and foreground colors")
        }
        Button(
            onClick = {},
            border = BorderStroke(5.dp, Color.Black),
        ) {
            Text("I am a button with a border")
        }
    }
}

@Preview
@Composable
fun PreviewButtons() {
    Buttons()
}