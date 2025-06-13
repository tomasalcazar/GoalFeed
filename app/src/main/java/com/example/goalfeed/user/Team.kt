package com.example.goalfeed.user

data class TeamsApiResponse(
    val response: List<TeamEntry>
)

data class TeamEntry(
    val team: TeamDetail
)

data class TeamDetail(
    val id: Int,
    val name: String,
    val logo: String
)
