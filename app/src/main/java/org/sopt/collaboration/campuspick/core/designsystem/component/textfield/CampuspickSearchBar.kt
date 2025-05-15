package org.sopt.collaboration.campuspick.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.extension.ignoreNextModifiers
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun CampuspickSearchBar(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    minLines: Int = 1,
    maxLength: Int = 25,
    usedOnlyNavigation: Boolean,
    onSearchClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp),
        value = value,
        onValueChange = { newValue ->
            if (newValue.replace(" ", "").length <= maxLength) onValueChange(newValue)
        },
        singleLine = maxLines == 1,
        maxLines = if (minLines > maxLines) minLines else maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = CampuspickTheme.typography.body0.copy(
            color = CampuspickTheme.colors.Black
        ),
        cursorBrush = SolidColor(CampuspickTheme.colors.Black),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(color = CampuspickTheme.colors.Gray4)
                    .fillMaxSize()
                    .let { if (usedOnlyNavigation) it else it.ignoreNextModifiers() }
                    .customClickable(
                        rippleEnabled = false,
                        onClick = onSearchClick
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = CampuspickTheme.colors.Gray2,
                                style = CampuspickTheme.typography.body0
                            )
                        }
                        innerTextField()
                    }
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                        tint = Color.Unspecified,
                        contentDescription = "search",
                    )
                }
            }
        }
    )
}

@DefaultPreview
@Composable
private fun SearchBarPreview() {
    CampuspickTheme {
        var text by remember { mutableStateOf("") }
        CampuspickSearchBar(
            placeholder = "찾으시는 동아리가 있나요?",
            value = text,
            onValueChange = { text = it },
            usedOnlyNavigation = true,
            onSearchClick = {}
        )
    }
}