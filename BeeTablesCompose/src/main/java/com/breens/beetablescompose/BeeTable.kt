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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.utils.darkColor
import com.breens.beetablescompose.utils.extractMembers
import com.breens.beetablescompose.utils.lightColor
import com.breens.beetablescompose.utils.lightGray

/**
 * A highly customizable and performant two-way scrollable table component for Jetpack Compose.
 * This implementation uses LazyColumn for efficient vertical rendering of rows and a shared
 * horizontalScroll state for synchronized horizontal scrolling of the header and all rows.
 *
 * @param T The data class type for the table rows.
 * @param headerTableTitles The list of titles for the table header. Defaults to an empty list.
 * @param tableData The list of data class instances to display in the table.
 * @param outlinedTable Whether the table should be wrapped in an OutlinedCard. Defaults to true.
 * @param outlinedCardBorder The BorderStroke for the OutlinedCard. Defaults to a 1.dp border.
 * @param outlinedCardShape The shape of the OutlinedCard. Defaults to 0.dp rounded corners.
 * @param outlinedCardColor The container color of the OutlinedCard. Defaults to transparent.
 * @param disableVerticalDividers A boolean to control whether vertical dividers should be disabled.
 * Defaults to false.
 * @param dividerThickness The thickness of the table dividers. Defaults to 1.dp.
 * @param rowTextStyle The text style for the table rows. Defaults to MaterialTheme.typography.bodySmall.
 * @param rowBackGroundColors A list of two colors for alternating row backgrounds. Defaults to light and dark colors.
 * @param horizontalDividerColor The color of the horizontal dividers. Defaults to lightGray().
 * @param headerTitlesTextStyle The text style for the header titles. Defaults to MaterialTheme.typography.labelMedium.
 * @param headerTitlesBackGroundColor The background color for the header row. Defaults to lightGray().
 * @param rowBorderColor The color of the vertical row borders. Defaults to lightGray().
 * @param headerTitlesBorderColor The color of the vertical header borders. Defaults to darkColor().
 * @param contentAlignment The alignment for all cell contents. Defaults to Alignment.Center.
 * @param textAlign The text alignment for all cell text. Defaults to TextAlign.Center.
 * @param tablePadding The padding for the table. Defaults to 0.dp.
 * @param columnToIndexIncreaseWidth The index of the column to have increased width. Defaults to null.
 *
 */
@ExperimentalBeeTableApi
@Composable
inline fun <reified T : Any> BeeTable(
    headerTableTitles: List<String> = emptyList(),
    tableData: List<T>,
    outlinedTable: Boolean = true,
    outlinedCardBorder: BorderStroke? = CardDefaults.outlinedCardBorder(),
    outlinedCardShape: Dp = 0.dp,
    outlinedCardColor: Color = Color.Transparent,
    disableVerticalDividers: Boolean = false,
    dividerThickness: Dp = 1.dp,
    rowTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    rowBackGroundColors: List<Color> = listOf(lightColor(), lightGray()),
    horizontalDividerColor: Color = lightGray(),
    headerTitlesTextStyle: TextStyle = MaterialTheme.typography.labelMedium,
    headerTitlesBackGroundColor: Color = lightGray(),
    rowBorderColor: Color = lightGray(),
    headerTitlesBorderColor: Color = darkColor(),
    contentAlignment: Alignment = Alignment.CenterStart,
    textAlign: TextAlign = TextAlign.Start,
    tablePadding: Dp = 0.dp,
    columnToIndexIncreaseWidth: Int? = null,
    modifier: Modifier = Modifier,
    enableHeaderTitles: Boolean = true,
) {
    /* The horizontal scroll state will be shared between the header and all rows*/
    val horizontalScrollState = rememberScrollState()

    val tableContent = @Composable {
        Column(
            modifier = modifier,
        ) {
            /* Table Header - A regular Row that scrolls horizontally*/
            AnimatedVisibility(enableHeaderTitles) {
                Row(
                    modifier = Modifier
                        .background(headerTitlesBackGroundColor)
                        .horizontalScroll(horizontalScrollState),
                ) {
                    headerTableTitles.forEachIndexed { index, title ->
                        val columnWidth =
                            if (index == columnToIndexIncreaseWidth) 200.dp else 120.dp
                        Box(
                            modifier = Modifier
                                .width(columnWidth)
                                .border(
                                    width = dividerThickness,
                                    color = headerTitlesBorderColor,
                                ),
                            contentAlignment = contentAlignment,
                        ) {
                            Text(
                                text = title,
                                style = headerTitlesTextStyle,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .height(38.dp)
                                    .wrapContentHeight(),
                                textAlign = textAlign,
                            )
                        }
                    }
                }
            }

            /* Table Body - A LazyColumn for vertical scrolling of rows*/
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(tableData.indices.toList()) { index ->
                    val rowData = extractMembers(tableData[index])
                    val tableRowBackgroundColor = if (index % 2 == 0) {
                        rowBackGroundColors[0]
                    } else {
                        rowBackGroundColors[1]
                    }

                    /*Each row is a regular Row that shares the same horizontal scroll state*/
                    Row(
                        modifier = Modifier
                            .background(tableRowBackgroundColor)
                            .horizontalScroll(horizontalScrollState)
                            .padding(tablePadding),
                    ) {
                        rowData.forEachIndexed { memberIndex, member ->
                            val columnWidth =
                                if (memberIndex == columnToIndexIncreaseWidth) 200.dp else 120.dp
                            Box(
                                modifier = Modifier
                                    .width(columnWidth)
                                    .then(
                                        if (!disableVerticalDividers) {
                                            Modifier.border(
                                                width = dividerThickness,
                                                color = rowBorderColor,
                                            )
                                        } else {
                                            Modifier
                                        },
                                    ),
                                contentAlignment = contentAlignment,
                            ) {
                                Text(
                                    text = member.second,
                                    style = rowTextStyle,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .height(38.dp)
                                        .wrapContentHeight(),
                                    textAlign = textAlign,
                                )
                            }
                        }
                    }

                    /* A horizontal divider for each row, if not disabled*/
                    if (disableVerticalDividers) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dividerThickness)
                                .background(horizontalDividerColor),
                        )
                    }
                }
            }
        }
    }

    if (outlinedTable) {
        if (outlinedCardBorder != null) {
            OutlinedCard(
                border = outlinedCardBorder,
                shape = RoundedCornerShape(outlinedCardShape),
                colors = CardDefaults.cardColors(containerColor = outlinedCardColor),
            ) {
                tableContent()
            }
        }
    } else {
        tableContent()
    }
}

/*
Expounded The Class For Previewing More Columns
 */
data class FootballTeam(
    val team: String,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int,
    val cleanSheets: Int,
    val yellowCards: Int,
    val redCards: Int,
    val possession: Double,
    val passes: Int,
    val points: Int,
)

@OptIn(ExperimentalBeeTableApi::class)
@Composable
@Preview(showBackground = true)
fun TwoWayScrollableTablePreview() {
    val teams = listOf(
        FootballTeam("Man Utd", 26, 7, 5, 87, 30, 57, 18, 54, 2, 60.5, 2345, 95),
        FootballTeam("Liverpool", 28, 5, 5, 85, 32, 53, 16, 50, 1, 62.1, 2450, 92),
        FootballTeam("Man City", 27, 8, 3, 90, 25, 65, 20, 45, 0, 65.3, 2600, 91),
        FootballTeam("Arsenal", 25, 10, 3, 78, 28, 50, 15, 60, 3, 58.0, 2200, 88),
        FootballTeam("Chelsea", 24, 11, 3, 75, 30, 45, 14, 55, 1, 57.5, 2150, 85),
        FootballTeam("Tottenham", 23, 12, 3, 72, 35, 37, 12, 65, 2, 55.0, 2000, 82),
        FootballTeam("West Ham", 22, 13, 3, 68, 40, 28, 10, 70, 4, 50.0, 1800, 79),
        FootballTeam("Leicester", 21, 14, 3, 65, 45, 20, 9, 75, 3, 48.5, 1750, 76),
        FootballTeam("Newcastle", 20, 15, 3, 60, 50, 10, 8, 80, 5, 45.0, 1600, 73),
        FootballTeam("Everton", 19, 16, 3, 55, 55, 0, 7, 85, 4, 43.0, 1550, 70),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
        FootballTeam("Aston Villa", 18, 17, 3, 50, 60, -10, 6, 90, 6, 40.0, 1400, 67),
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

    // The header titles are now derived from the data class member names
    val headerTitles =
        extractMembers(teams.first()).map { it.first.replaceFirstChar { it.uppercase() } }

    BeeTable(
        headerTableTitles = headerTitles,
        tableData = teams,
        rowBackGroundColors = listOf(lightColor(), lightGray()),
        headerTitlesBackGroundColor = lightGray(),
        rowBorderColor = lightGray(),
        columnToIndexIncreaseWidth = 0, // Make the first column wider
        outlinedTable = true,
        outlinedCardShape = 8.dp,
        outlinedCardBorder = BorderStroke(2.dp, darkColor()),
        contentAlignment = Alignment.Center,
    )
}
