package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubSortBottomSheet(
    showClubSortBottomSheet: Boolean,
    onDismiss: (Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val selectedSort = remember { mutableStateOf("최신순") }

    LaunchedEffectWithLifecycle {
        if (showClubSortBottomSheet) {
            coroutineScope.launch {
                bottomSheetState.show()
            }
        }
    }
    if (showClubSortBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }.invokeOnCompletion {
                    onDismiss(false)
                }
            },
            sheetState = bottomSheetState,
            dragHandle = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CampuspickTheme.colors.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    BottomSheetDefaults.DragHandle()
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .background(CampuspickTheme.colors.White),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .background(CampuspickTheme.colors.White),
                ) {
                    listOf("최신순", "인기순", "관련도순", "거리순").forEach { option ->
                        SortedCategory(
                            text = option,
                            isSelected = selectedSort.value == option,
                            onClick = { selectedSort.value = option }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SortedCategory(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .customClickable(
                rippleEnabled = false,
                onClick = onClick
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = CampuspickTheme.typography.body3.copy(fontWeight = FontWeight.Bold),
                color = CampuspickTheme.colors.Black,
            )
            Spacer(modifier = Modifier.weight(1f))

            if (isSelected)
                Icon(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = "filter",
                    tint = Color.Unspecified,
                )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            thickness = 2.dp,
            color = CampuspickTheme.colors.Gray4
        )
    }
}