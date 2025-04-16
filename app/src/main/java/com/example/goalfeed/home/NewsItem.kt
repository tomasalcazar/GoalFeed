package com.example.goalfeed.home

data class NewsItem(
    val title: String,
    val source: Source,
    val urlToImage: String?,
    val description: String?
)

data class Source(
    val name: String
)

data class NewsApiResponse(
    val articles: List<NewsItem>
)
