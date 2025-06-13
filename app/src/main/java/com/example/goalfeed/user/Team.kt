package com.example.goalfeed.user

data class TeamsApiResponse(
    val response: List<NbaTeamEntry>
)

data class NbaTeamEntry(
    val id: Int,
    val name: String,
    val logo: String
)
