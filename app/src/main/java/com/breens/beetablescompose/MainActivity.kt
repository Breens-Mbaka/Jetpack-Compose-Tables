package com.breens.beetablescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.utils.premierLeagueTeams
import com.breens.beetablescompose.utils.titles

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                contentAlignment = Alignment.Center,
            ) {
                BeeTablesCompose(
                    data = premierLeagueTeams,
                    headerTableTitles = titles,
                    tableRowColors = listOf(Color.White, Color(0XFFE9AB17)),
                )
            }
        }
    }
}
