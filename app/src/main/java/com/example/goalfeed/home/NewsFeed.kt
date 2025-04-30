package com.example.goalfeed.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.goalfeed.ui.theme.*

@Composable
fun NewsFeed(newsList: List<NewsItem>, onClick: (NewsItem) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = paddingLarge)
    ) {
        item { Spacer(Modifier.padding(spacingXXS)) }
        items(newsList) { newsItem ->
            NewsCard(newsItem = newsItem) {
                onClick(newsItem)
            }
        }
        item { Spacer(Modifier.padding(spacingXXL)) }
    }
}