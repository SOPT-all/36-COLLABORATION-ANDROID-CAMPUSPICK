package org.sopt.collaboration.campuspick.core.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun PopularChip(
    buttonText: String
) {
    CampuspickBasicButton(
        buttonText = buttonText,
        onClick = { },
        paddingValues = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
        textStyle = CampuspickTheme.typography.caption2,
        textColor = CampuspickTheme.colors.Blue,
        backgroundColor = CampuspickTheme.colors.White,
        outlineColor = CampuspickTheme.colors.Blue,
    )
}

@DefaultPreview
@Composable
private fun PopularChipPreview() {
    CampuspickTheme {
        PopularChip(
            buttonText = "버튼"
        )
    }
}