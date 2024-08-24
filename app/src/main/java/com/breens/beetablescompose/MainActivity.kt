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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.ui.theme.BeeTablesComposeTheme
import com.breens.beetablescompose.utils.premierLeagueTeams
import com.breens.beetablescompose.utils.titles

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeTablesComposeTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    var disableVerticalDividers by remember {
                        mutableStateOf(false)
                    }
                    var horizontalDividerThickness by remember {
                        mutableStateOf(0.6f)
                    }

                    var enableHeaderTitles by remember {
                        mutableStateOf(false)
                    }

                    var centerContent by remember {
                        mutableStateOf(false)
                    }

                    var centerTextAlignment by remember {
                        mutableStateOf(false)
                    }

                    var increaseColumnWidth by remember {
                        mutableStateOf<Int?>(null)
                    }

                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        item {
                            BeeTablesCompose(
                                data = premierLeagueTeams,
                                enableTableHeaderTitles = enableHeaderTitles,
                                disableVerticalDividers = disableVerticalDividers,
                                dividerThickness = horizontalDividerThickness.dp,
                                columnToIndexIncreaseWidth = increaseColumnWidth?.minus(1),
                                headerTableTitles = titles,
                                headerTitlesBackGroundColor = Color(0XFFE9AB17),
                                tableRowColors = listOf(
                                    MaterialTheme.colorScheme.surface,
                                    MaterialTheme.colorScheme.surface,
                                ),
                                contentAlignment = if (centerContent) Alignment.Center else Alignment.CenterStart,
                                textAlign = if (centerTextAlignment) TextAlign.Center else TextAlign.Start,
                            )

                            Spacer(modifier = Modifier.padding(12.dp))
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
                                    onCheckedChange = {
                                        enableHeaderTitles = it
                                    },
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
                                    onCheckedChange = {
                                        disableVerticalDividers = it
                                    },
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

                                Slider(value = horizontalDividerThickness, onValueChange = {
                                    horizontalDividerThickness = it
                                })
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
                                    onCheckedChange = {
                                        centerContent = it
                                    },
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
                                    onCheckedChange = {
                                        centerTextAlignment = it
                                    },
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
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(text = "1", style = MaterialTheme.typography.bodySmall)
                                    Checkbox(
                                        checked = increaseColumnWidth == 1,
                                        onCheckedChange = {
                                            increaseColumnWidth = if (it) 1 else null
                                        },
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(text = "2", style = MaterialTheme.typography.bodySmall)
                                    Checkbox(
                                        checked = increaseColumnWidth == 2,
                                        onCheckedChange = {
                                            increaseColumnWidth = if (it) 2 else null
                                        },
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(text = "3", style = MaterialTheme.typography.bodySmall)
                                    Checkbox(
                                        checked = increaseColumnWidth == 3,
                                        onCheckedChange = {
                                            increaseColumnWidth = if (it) 3 else null
                                        },
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(text = "4", style = MaterialTheme.typography.bodySmall)
                                    Checkbox(
                                        checked = increaseColumnWidth == 4,
                                        onCheckedChange = {
                                            increaseColumnWidth = if (it) 4 else null
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
