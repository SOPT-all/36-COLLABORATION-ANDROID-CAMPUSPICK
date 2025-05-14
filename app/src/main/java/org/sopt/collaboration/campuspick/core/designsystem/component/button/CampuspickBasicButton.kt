package org.sopt.collaboration.campuspick.core.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun CampuspickBasicButton(
    buttonText: String,
    onClick: () -> Unit,
    paddingValues: PaddingValues,
    textStyle: TextStyle,
    textColor: Color,
    backgroundColor: Color,
    outlineColor: Color,
    modifier: Modifier = Modifier,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .customClickable(
                rippleEnabled = false,
                onClick = onClick
            )
            .clip(RoundedCornerShape(25.dp))
            .background(backgroundColor)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = outlineColor
                ),
                shape = RoundedCornerShape(25.dp)
            )
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buttonText,
            color = textColor,
            style = textStyle,
        )
        content?.invoke(this)
    }
}

@DefaultPreview
@Composable
private fun BasicButtonPreview() {
    CampuspickTheme {
        CampuspickBasicButton(
            buttonText = "버튼",
            onClick = {},
            paddingValues = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
            textStyle = CampuspickTheme.typography.body2,
            textColor = CampuspickTheme.colors.White,
            backgroundColor = CampuspickTheme.colors.Blue,
            outlineColor = Color.Transparent,
            content = null
        )
    }
}