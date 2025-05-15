package org.sopt.collaboration.campuspick.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.component.button.CampuspickBasicButton
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchKeyword(
    title: String,
    keywords: List<String>,
    modifier: Modifier,
    keywordType: (@Composable (String) -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = CampuspickTheme.typography.body2.copy(fontWeight = FontWeight.ExtraBold),
            color = CampuspickTheme.colors.Black,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            keywords.forEach {
                keywordType?.invoke(it)
            }
        }
    }
}

@Composable
fun RecentSearchKeyword(
    buttonText: String,
    textColor: Color,
    outlineColor: Color,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    Row {
        CampuspickBasicButton(
            buttonText = buttonText,
            onClick = { },
            paddingValues = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
            textStyle = CampuspickTheme.typography.caption2,
            textColor = textColor,
            backgroundColor = CampuspickTheme.colors.White,
            outlineColor = outlineColor,
            content = content
        )
    }
}