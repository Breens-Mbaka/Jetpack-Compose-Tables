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
