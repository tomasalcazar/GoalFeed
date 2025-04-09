package com.example.goalfeed.matches

data class MatchItem (
    val date: String,
    val time: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val status: String, // "Live", "FT", "Upcoming"
)
