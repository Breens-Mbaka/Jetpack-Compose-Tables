package com.breens.beetablescompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.components.TableHeaderComponent
import com.breens.beetablescompose.components.TableRowComponent
import com.breens.beetablescompose.utils.extractMembers

/**
 * 🐝 A Compose UI data table library.
 *
 * @param data The list of data items to display in the table.
 * @param headerTableTitles The list of header titles to display at the top of the table.
 * @param headerTitlesBorderColor The color of the border for the header titles, by default it will be [Color.LightGray].
 * @param headerTitlesBorderWidth The width of the border for the header titles in DP, by default it will be "0.4.dp".
 * @param headerTitlesTextStyle The text style to apply to the header titles, by default it will be [MaterialTheme.typography.bodySmall].
 * @param headerTitlesBackGroundColor The background color for the header titles, by default it will be [Color.White].
 * @param tableRowColors The list of background colors to alternate between rows in the table, by default it will be a list of: [Color.White], [Color.White].
 * @param rowBorderColor The color of the border for the table rows, by default it will be [Color.LightGray].
 * @param rowBorderWidth The width of the border for the table rows in DP, by default it will be "0.4.dp".
 * @param rowTextStyle The text style to apply to the data cells in the table rows, by default it will be [MaterialTheme.typography.bodySmall].
 * @param tableElevation The elevation of the entire table (Card elevation) in DP, by default it will be "6.dp".
 * @param shape The shape of the table's corners, by default it will be "RoundedCornerShape(4.dp)".
 */
@Composable
inline fun <reified T : Any> BeeTablesCompose(
    data: List<T>,
    headerTableTitles: List<String>,
    headerTitlesBorderColor: Color = Color.LightGray,
    headerTitlesBorderWidth: Dp = 0.4.dp,
    headerTitlesTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    headerTitlesBackGroundColor: Color = Color.White,
    tableRowColors: List<Color> = listOf(Color.White, Color.White),
    rowBorderColor: Color = Color.LightGray,
    rowBorderWidth: Dp = 0.4.dp,
    rowTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    tableElevation: Dp = 6.dp,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = tableElevation)) {
        Column(modifier = Modifier.clip(shape = shape)) {
            TableHeaderComponent(
                headerTableTitles = headerTableTitles,
                headerTitlesBorderColor = headerTitlesBorderColor,
                headerTitlesBorderWidth = headerTitlesBorderWidth,
                headerTitlesTextStyle = headerTitlesTextStyle,
                headerTitlesBackGroundColor = headerTitlesBackGroundColor,
            )

            data.forEachIndexed { index, data ->
                val rowData = extractMembers(data).map {
                    it.second // getting the value from the returned Pair
                }

                // alternate background colors between rows
                val tableRowBackgroundColor = if (index % 2 == 0) {
                    tableRowColors[0]
                } else {
                    tableRowColors[1]
                }

                TableRowComponent(
                    data = rowData,
                    rowBorderColor = rowBorderColor,
                    rowBorderWidth = rowBorderWidth,
                    rowTextStyle = rowTextStyle,
                    rowBackGroundColor = tableRowBackgroundColor,
                )
            }
        }
    }
}
