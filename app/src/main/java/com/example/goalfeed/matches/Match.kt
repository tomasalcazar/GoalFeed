package com.example.goalfeed.matches

data class MatchesApiResponse(
    val matches: List<Match>
)

data class Match(
    val utcDate: String,
    val status: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score
)

data class Team(val name: String)

data class Score(val fullTime: FullTimeScore)

data class FullTimeScore(val home: Int?, val away: Int?)

