package com.example.goalfeed.matches

data class Match(
    val utcDate: String,
    val status: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score
)

data class Team(
    val id: Int,
    val name: String,
    val logo: String? = null
)

data class Score(val fullTime: FullTimeScore)
data class FullTimeScore(val home: Int?, val away: Int?)
