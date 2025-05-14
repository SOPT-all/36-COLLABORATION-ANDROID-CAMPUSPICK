package org.sopt.collaboration.campuspick.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.core.designsystem.component.button.FilterChip
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.DeadLine.Companion.fromLabel
import org.sopt.collaboration.campuspick.domain.model.FilterSection
import org.sopt.collaboration.campuspick.domain.model.Location
import org.sopt.collaboration.campuspick.domain.model.Location.Companion.fromLabel
import org.sopt.collaboration.campuspick.domain.model.PreferDay
import org.sopt.collaboration.campuspick.domain.model.PreferDay.Companion.fromLabel
import org.sopt.collaboration.campuspick.feature.search.component.SearchKeyword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    showFilterBottomSheet: Boolean,
    onDismiss: (Boolean) -> Unit,
    bottomSheetDeadLineSelected: String,
    updateSelectedDeadLine: (DeadLine) -> Unit,
    bottomSheetLocationSelected: String,
    updateSelectedLocation: (Location) -> Unit,
    bottomSheetPreferDaySelected: String,
    updateSelectedPreferDay: (PreferDay) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffectWithLifecycle {
        if (showFilterBottomSheet) {
            coroutineScope.launch {
                bottomSheetState.show()
            }
        }
    }

    if (showFilterBottomSheet) {
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
                    val filterSection = listOf<FilterSection>(
                        FilterSection(
                            title = "마감 기한",
                            keywords = DeadLine.entries.mapNotNull { it.label.takeIf { label -> label.isNotBlank() } },
                            keywordType = { label: String ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetDeadLineSelected == label,
                                    onClick = {
                                        DeadLine.fromLabel(label)?.let(updateSelectedDeadLine)
                                    }
                                )
                            }
                        ),
                        FilterSection(
                            title = "지역",
                            keywords = Location.entries.mapNotNull { it.label.takeIf { label -> label.isNotBlank() } },
                            keywordType = { label ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetLocationSelected == label,
                                    onClick = {
                                        Location.fromLabel(label)?.let(updateSelectedLocation)
                                    }
                                )
                            }
                        ),
                        FilterSection(
                            title = "활동 선호 요일",
                            keywords = PreferDay.entries.mapNotNull { it.label.takeIf { label -> label.isNotBlank() } },
                            keywordType = { label ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetPreferDaySelected == label,
                                    onClick = {
                                        PreferDay.fromLabel(label)?.let(updateSelectedPreferDay)
                                    }
                                )
                            }
                        )
                    )

                    filterSection.forEach { section ->
                        SearchKeyword(
                            title = section.title,
                            keywords = section.keywords,
                            modifier = Modifier.padding(bottom = 30.dp),
                            keywordType = section.keywordType
                        )
                    }
                }
            }
        }
    }
}