package com.example.goalfeed

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.home.NewsFeed
import com.example.goalfeed.view.models.NewsViewModel

@Composable
fun MainMenu(onClick: (Int) -> Unit) {
    val viewModel = hiltViewModel<NewsViewModel>()
    val news by viewModel.news.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val retry by viewModel.showRetry.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            retry -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error loading news", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = viewModel::retryApiCall) {
                        Text("Retry")
                    }
                }
            }

            news.isEmpty() -> {
                Text("No news available", modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                NewsFeed(newsList = news) { newsItem ->
                    val index = news.indexOf(newsItem)
                    onClick(index)
                }
            }
        }
    }
}
