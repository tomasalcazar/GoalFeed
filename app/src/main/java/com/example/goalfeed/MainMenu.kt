package com.example.goalfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.home.NewsFeed
import com.example.goalfeed.view.models.NewsViewModel

@Composable
fun MainMenu(onClick: (String) -> Unit) {
    val viewModel = hiltViewModel<NewsViewModel>()
    val news by viewModel.news.collectAsState()

    Column {
        NewsFeed(newsList = news) {
            onClick(it.title)
        }
    }
}
