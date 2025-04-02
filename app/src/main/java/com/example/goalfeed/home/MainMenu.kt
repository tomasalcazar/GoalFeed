package com.example.goalfeed.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenu(onClick: (String) -> Unit) {
    val newsList = listOf(
        NewsItem("Title 1", "Example.com", "", "Short description..."),
        NewsItem("Title 2", "Example.com", "", "Another short description...") ,
        NewsItem("Title 3", "Example.com", "", "Another short description..."),
        NewsItem("Title 4", "Example.com", "", "Another short description..."),
        NewsItem("Title 5", "Example.com", "", "Another short description..."),
        NewsItem("Title 6", "Example.com", "", "Another short description...")
    )

    Column {
        Text(
            "Home",
            fontSize = 34.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        )
        NewsFeed(newsList) {
            onClick(it.title)
        }
    }
}

@Preview
@Composable
fun PreviewMainMenu() {
    MainMenu {}
}