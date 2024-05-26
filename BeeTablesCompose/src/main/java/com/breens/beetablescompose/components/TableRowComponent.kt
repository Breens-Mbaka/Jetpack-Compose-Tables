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
package com.breens.beetablescompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
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
import com.breens.beetablescompose.utils.lightColor
import com.breens.beetablescompose.utils.lightGray

@Composable
fun TableRowComponent(
    data: List<String>,
    rowBorderColor: Color,
    rowTextStyle: TextStyle,
    rowBackGroundColor: Color,
    contentAlignment: Alignment,
    textAlign: TextAlign,
    tablePadding: Dp,
    columnToIndexIncreaseWidth: Int?,
    dividerThickness: Dp,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(rowBackGroundColor)
            .padding(tablePadding),
    ) {
        data.forEachIndexed { index, title ->
            val weight = if (index == columnToIndexIncreaseWidth) 8f else 2f
            Box(
                modifier = Modifier
                    .weight(weight)
                    .border(
                        width = dividerThickness,
                        color = rowBorderColor,
                    ),
                contentAlignment = contentAlignment,
            ) {
                Text(
                    text = title,
                    style = rowTextStyle,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .height(38.dp)
                        .wrapContentHeight()
                        .padding(end = 8.dp),
                    textAlign = textAlign,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TableRowComponentPreview() {
    val titles = listOf("Man Utd", "26", "7", "95")

    TableRowComponent(
        data = titles,
        rowBorderColor = lightGray(),
        rowTextStyle = MaterialTheme.typography.bodySmall,
        rowBackGroundColor = lightColor(),
        contentAlignment = Alignment.Center,
        textAlign = TextAlign.Center,
        tablePadding = 0.dp,
        columnToIndexIncreaseWidth = null,
        dividerThickness = 1.dp,
    )
}
