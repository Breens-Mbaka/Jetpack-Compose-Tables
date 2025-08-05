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
package com.breens.beetablescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.ui.theme.BeeTablesComposeTheme
import com.breens.beetablescompose.utils.darkColor
import com.breens.beetablescompose.utils.extractMembers
import com.breens.beetablescompose.utils.lightColor
import com.breens.beetablescompose.utils.lightGray
import com.breens.beetablescompose.utils.premierLeagueTeams
import com.breens.beetablescompose.utils.titles

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeTablesComposeTheme {
                val selectedTabIndex = rememberSaveable { mutableStateOf(0) }
                val tabs = listOf("BeeTablesCompose", "TwoWayScrollableTable")
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text("Table Samples") })
                        },
                    ) { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            TabRow(
                                selectedTabIndex = selectedTabIndex.value,
                                modifier = Modifier.padding(16.dp),
                            ) {
                                tabs.forEachIndexed { index, text ->
                                    Tab(
                                        selected = index == selectedTabIndex.value,
                                        text = { Text(text = text) },
                                        onClick = { selectedTabIndex.value = index },
                                    )
                                }
                            }
                            when (selectedTabIndex.value) {
                                0 -> MainUi()
                                1 -> TwoWayScrollableTable()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainUi(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        var disableVerticalDividers by remember { mutableStateOf(false) }
        var horizontalDividerThickness by remember { mutableStateOf(0.6f) }
        var enableHeaderTitles by remember { mutableStateOf(false) }
        var centerContent by remember { mutableStateOf(false) }
        var centerTextAlignment by remember { mutableStateOf(false) }
        var increaseColumnWidth by remember { mutableStateOf<Int?>(null) }

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            item {
                BeeTablesCompose(
                    data = premierLeagueTeams,
                    enableTableHeaderTitles = enableHeaderTitles,
                    disableVerticalDividers = disableVerticalDividers,
                    dividerThickness = horizontalDividerThickness.dp,
                    columnToIndexIncreaseWidth = increaseColumnWidth?.minus(1),
                    headerTableTitles = titles,
                    headerTitlesBackGroundColor = Color(0xFFE9AB17),
                    tableRowColors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface,
                    ),
                    contentAlignment = if (centerContent) Alignment.Center else Alignment.CenterStart,
                    textAlign = if (centerTextAlignment) TextAlign.Center else TextAlign.Start,
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Enable Table Header Titles",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Switch(
                        checked = enableHeaderTitles,
                        onCheckedChange = { enableHeaderTitles = it },
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Hide Vertical Divider",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Switch(
                        checked = disableVerticalDividers,
                        onCheckedChange = { disableVerticalDividers = it },
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Divider Thickness",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Slider(
                        value = horizontalDividerThickness,
                        onValueChange = { horizontalDividerThickness = it },
                        valueRange = 0.1f..2.0f,
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Center Content",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Switch(
                        checked = centerContent,
                        onCheckedChange = { centerContent = it },
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Text Alignment Center",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Switch(
                        checked = centerTextAlignment,
                        onCheckedChange = { centerTextAlignment = it },
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
            }
            item {
                Column {
                    Text(
                        text = "The Column To Increase Width",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    (1..4).forEach { column ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(text = "$column", style = MaterialTheme.typography.bodySmall)
                            Checkbox(
                                checked = increaseColumnWidth == column,
                                onCheckedChange = {
                                    increaseColumnWidth = if (it) column else null
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalBeeTableApi::class)
@Composable
fun TwoWayScrollableTable() {
    val teams = listOf(
        FootballTeam("Arsenal", 25, 10, 3, 78, 28, 50, 15, 60, 3, 58.0, 2200, 88),
        FootballTeam("Chelsea", 24, 11, 3, 75, 30, 45, 14, 55, 1, 57.5, 2150, 85),
        FootballTeam("Tottenham", 23, 12, 3, 72, 35, 37, 12, 65, 2, 55.0, 2000, 82),
        FootballTeam("West Ham", 22, 13, 3, 68, 40, 28, 10, 70, 4, 50.0, 1800, 79),
        FootballTeam("Leicester", 21, 14, 3, 65, 45, 20, 9, 75, 3, 48.5, 1750, 76),
        FootballTeam("Newcastle", 20, 15, 3, 60, 50, 10, 8, 80, 5, 45.0, 1600, 73),
        FootballTeam("Everton", 19, 16, 3, 55, 55, 0, 7, 85, 4, 43.0, 1550, 70),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Leeds", 17, 18, 3, 45, 65, -20, 5, 95, 5, 38.0, 1300, 64),
        FootballTeam("Wolves", 16, 19, 3, 40, 70, -30, 4, 100, 7, 35.0, 1200, 61),
        FootballTeam("Crystal Palace", 15, 20, 3, 35, 75, -40, 3, 105, 6, 33.0, 1100, 58),
        FootballTeam("Southampton", 14, 21, 3, 30, 80, -50, 2, 110, 8, 30.0, 1000, 55),
        FootballTeam("Brighton", 13, 22, 3, 25, 85, -60, 1, 115, 7, 28.0, 900, 52),
        FootballTeam("Brentford", 12, 23, 3, 20, 90, -70, 0, 120, 9, 25.0, 800, 49),
        FootballTeam("Burnley", 11, 24, 3, 15, 95, -80, 0, 125, 8, 23.0, 700, 46),
        FootballTeam("Watford", 10, 25, 3, 10, 100, -90, 0, 130, 10, 20.0, 600, 43),
        FootballTeam("Norwich", 9, 26, 3, 5, 105, -100, 0, 135, 9, 18.0, 500, 40),
    )

    val headerTitles = extractMembers(teams.first()).map { it.first.replaceFirstChar { it.uppercase() } }
    var disableVerticalDividers by remember { mutableStateOf(false) }
    var horizontalDividerThickness by remember { mutableStateOf(0.6f) }
    var enableHeaderTitles by remember { mutableStateOf(true) }
    var centerContent by remember { mutableStateOf(false) }
    var centerTextAlignment by remember { mutableStateOf(false) }
    var increaseColumnWidth by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        item {
            BeeTablesCompose(
                modifier = Modifier.heightIn(max = 400.dp),
                headerTableTitles = headerTitles,
                tableData = teams,
                rowBackGroundColors = listOf(lightColor(), lightGray()),
                headerTitlesBackGroundColor = lightGray(),
                rowBorderColor = lightGray(),
                columnToIndexIncreaseWidth = increaseColumnWidth?.minus(1),
                outlinedTable = true,
                outlinedCardShape = 8.dp,
                outlinedCardBorder = BorderStroke(2.dp, darkColor()),
                contentAlignment = if (centerContent) Alignment.Center else Alignment.CenterStart,
                textAlign = if (centerTextAlignment) TextAlign.Center else TextAlign.Start,
                enableHeaderTitles = enableHeaderTitles,
                disableVerticalDividers = disableVerticalDividers,
                dividerThickness = horizontalDividerThickness.dp,
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Enable Table Header Titles",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Switch(
                    checked = enableHeaderTitles,
                    onCheckedChange = { enableHeaderTitles = it },
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Hide Vertical Divider",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Switch(
                    checked = disableVerticalDividers,
                    onCheckedChange = { disableVerticalDividers = it },
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Divider Thickness",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Slider(
                    value = horizontalDividerThickness,
                    onValueChange = { horizontalDividerThickness = it },
                    valueRange = 0.1f..2.0f,
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Center Content",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Switch(
                    checked = centerContent,
                    onCheckedChange = { centerContent = it },
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Text Alignment Center",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Switch(
                    checked = centerTextAlignment,
                    onCheckedChange = { centerTextAlignment = it },
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
        }
        item {
            Column {
                Text(
                    text = "The Column To Increase Width",
                    style = MaterialTheme.typography.bodyMedium,
                )
                (1..4).forEach { column ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(text = "$column", style = MaterialTheme.typography.bodySmall)
                        Checkbox(
                            checked = increaseColumnWidth == column,
                            onCheckedChange = {
                                increaseColumnWidth = if (it) column else null
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainUiPreview() {
    BeeTablesComposeTheme {
        MainUi()
    }
}
