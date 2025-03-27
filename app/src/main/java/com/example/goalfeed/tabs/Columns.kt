package com.example.goalfeed.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Columns() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("I am a normal column with 2 texts")
        Column(
            modifier = Modifier.background(color = Color.Blue.copy(alpha = 0.25f))
        ) {
            Text("Title",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text("Subtitle")
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a column with 2 texts that fills the total width")
        Column(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue.copy(alpha = 0.25f))
        ) {
            Text("Title",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text("Subtitle")
        }
        Spacer(Modifier.size(20.dp))

        Text("I am a column with 2 texts that has a fixed width")
        Column(
            modifier = Modifier.width(200.dp).background(color = Color.Blue.copy(alpha = 0.25f))
        ) {
            Text("Title",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text("Subtitle")
        }
    }
}

@Preview
@Composable
fun PreviewColumns() {
    Columns()
}