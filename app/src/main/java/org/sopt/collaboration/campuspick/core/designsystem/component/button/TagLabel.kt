package org.sopt.collaboration.campuspick.core.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme

@Composable
fun TagLabel(
    buttonText: String
) {
    CampuspickBasicButton(
        buttonText = buttonText,
        clickable = false,
        onClick = { },
        paddingValues = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
        textStyle = CampuspickTheme.typography.caption4,
        textColor = CampuspickTheme.colors.Gray1,
        backgroundColor = CampuspickTheme.colors.Gray4,
        outlineColor = Color.Transparent,
    )
}

@Preview
@Composable
private fun PopularChipPreview() {
    CampuspickTheme {
        TagLabel(
            buttonText = "버튼"
        )
    }
}