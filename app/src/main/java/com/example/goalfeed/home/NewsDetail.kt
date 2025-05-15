package com.example.goalfeed.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.goalfeed.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetail(newsItem: NewsItem) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = newsItem.source.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    top = paddingExtraSmall,
                    bottom = paddingSmall,
                    start = paddingMedium,
                    end = paddingMedium
                )
        ) {
            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(spacingMedium))

            newsItem.urlToImage?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height200)
                )
            }

            Spacer(modifier = Modifier.height(spacingMedium))

            val fullText = buildString {
                append(newsItem.description.orEmpty())
                if (!newsItem.description.isNullOrBlank()) append("\n\n")
                append(newsItem.content.orEmpty().substringBefore("[+"))
            }

            Text(
                text = fullText.trim(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
