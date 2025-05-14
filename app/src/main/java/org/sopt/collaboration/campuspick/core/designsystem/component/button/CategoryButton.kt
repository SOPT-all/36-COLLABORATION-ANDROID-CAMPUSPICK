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
fun CategoryButton(
    buttonText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = CampuspickTheme.colors
    val typo = CampuspickTheme.typography
    val buttonColors = remember(isSelected) {
        derivedStateOf {
            Pair(
                if (isSelected) color.White else color.Gray1, // text
                if (isSelected) color.Blue else color.Gray4, // background
            )
        }
    }
    val textStyle = remember(isSelected) {
        derivedStateOf {
            if (isSelected) typo.body2 else typo.body1
        }
    }
    CampuspickBasicButton(
        buttonText = buttonText,
        onClick = onClick,
        paddingValues = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        textStyle = textStyle.value,
        textColor = buttonColors.value.first,
        backgroundColor = buttonColors.value.second,
        outlineColor = Color.Transparent,
    )
}

@DefaultPreview
@Composable
private fun FilterChipPreview() {
    CampuspickTheme {
        CategoryButton(
            buttonText = "버튼",
            isSelected = true,
            onClick = {}
        )
    }
}