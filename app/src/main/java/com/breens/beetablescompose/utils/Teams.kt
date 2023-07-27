package com.breens.beetablescompose.utils

data class Teams(
    val name: String,
    val homeWins: String,
    val awayWins: String,
    val totalPoints: String,
)

val premierLeagueTeams = listOf(
    Teams("Arsenal", "4", "2", "14"),
    Teams("Chelsea", "5", "3", "18"),
    Teams("Manchester United", "6", "1", "19"),
    Teams("Liverpool", "4", "4", "16"),
    // Add more teams as needed
)

val titles = listOf("Team", "Home", "Away", "Points")
