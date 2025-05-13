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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme

@Composable
fun CampuspickSearchBar(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
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
                IconButton(onClick = onSearchClick) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_club_search),
                        tint = CampuspickTheme.colors.Gray2,
                        contentDescription = "search",
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    CampuspickTheme {
        var text by remember { mutableStateOf("") }
        CampuspickSearchBar(
            placeholder = "찾으시는 동아리가 있나요?",
            value = text,
            onValueChange = { text = it },
            onSearchClick = {}
        )
    }
}