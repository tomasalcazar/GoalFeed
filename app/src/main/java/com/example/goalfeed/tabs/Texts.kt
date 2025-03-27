package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Texts() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            "I am a regular text"
        )
        Text(
            "I am a bold text",
            fontWeight = FontWeight.Bold,
        )
        Text(
            "I am a red text",
            color = Color.Red,
        )
        Text(
            "I am a italic text",
            fontStyle = FontStyle.Italic,
        )
        Text(
            "I am a large text",
            fontSize = 30.sp,
        )
        Text(
            "I am a small text",
            fontSize = 8.sp,
        )
    }
}

@Preview
@Composable
fun PreviewTexts() {
    Texts()
}