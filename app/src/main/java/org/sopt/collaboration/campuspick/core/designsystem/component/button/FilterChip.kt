package org.sopt.collaboration.campuspick.core.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun FilterChip(
    buttonText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = CampuspickTheme.colors
    val buttonColors = remember(isSelected) {
        derivedStateOf {
            Triple(
                if (isSelected) color.White else color.Gray1, // text
                if (isSelected) color.Blue else color.White, // background
                if (isSelected) Color.Transparent else color.Gray3 // outline
            )
        }
    }
    CampuspickBasicButton(
        buttonText = buttonText,
        onClick = onClick,
        paddingValues = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
        textStyle = CampuspickTheme.typography.caption2,
        textColor = buttonColors.value.first,
        backgroundColor = buttonColors.value.second,
        outlineColor = buttonColors.value.third,
    )
}

@DefaultPreview
@Composable
private fun FilterChipPreview() {
    CampuspickTheme {
        FilterChip(
            buttonText = "버튼",
            isSelected = true,
            onClick = {}
        )
    }
}