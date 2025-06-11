package com.example.goalfeed.matches

data class ApiFootballResponse(
    val response: List<ApiFootballMatch>
)

data class ApiFootballMatch(
    val fixture: FixtureInfo,
    val league: LeagueInfo,
    val teams: TeamsInfo,
    val goals: GoalsInfo,
    val score: ScoreInfo
)

data class FixtureInfo(
    val id: Int,
    val date: String,
    val status: StatusInfo,
    // otros campos si quieres
)

data class StatusInfo(
    val long: String,
    val short: String,
    val elapsed: Int?
)

data class LeagueInfo(
    val id: Int,
    val name: String,
    val country: String,
    val logo: String,
    val flag: String?,
    val season: Int,
    val round: String,
)

data class TeamsInfo(
    val home: TeamInfo,
    val away: TeamInfo
)

data class TeamInfo(
    val id: Int,
    val name: String,
    val logo: String,
    val winner: Boolean?
)

data class GoalsInfo(
    val home: Int?,
    val away: Int?
)

data class ScoreInfo(
    val halftime: ScoreDetail,
    val fulltime: ScoreDetail,
    val extratime: ScoreDetail,
    val penalty: ScoreDetail
)

data class ScoreDetail(
    val home: Int?,
    val away: Int?
)
