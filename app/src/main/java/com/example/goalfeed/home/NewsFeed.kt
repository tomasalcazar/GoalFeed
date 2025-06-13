package com.example.goalfeed.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.goalfeed.ui.theme.paddingLarge
import com.example.goalfeed.ui.theme.paddingMedium
import com.example.goalfeed.ui.theme.spacingXXS
import com.example.goalfeed.ui.theme.spacingXXL

@Composable
fun NewsFeed(newsList: List<NewsItem>, onClick: (NewsItem) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = paddingLarge),
        verticalArrangement   = Arrangement.spacedBy(paddingMedium),
        horizontalAlignment   = Alignment.CenterHorizontally
    ) {
        item { Spacer(Modifier.padding(spacingXXS)) }
        items(newsList) { newsItem ->
            NewsCard(newsItem) { onClick(newsItem) }
        }
        item { Spacer(Modifier.padding(spacingXXL)) }
    }
}
