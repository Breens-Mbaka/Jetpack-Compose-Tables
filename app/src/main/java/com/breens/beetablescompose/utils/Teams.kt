/*
 * Copyright 2023 Breens Mbaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
