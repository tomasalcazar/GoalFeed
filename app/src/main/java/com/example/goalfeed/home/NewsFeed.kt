package com.example.goalfeed.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NewsFeed(newsList: List<NewsItem>, onClick: (NewsItem) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        items(newsList) { newsItem ->
            NewsCard(newsItem = newsItem) {
                onClick(newsItem)
            }
        }
    }
}