package org.sopt.collaboration.campuspick.core.designsystem.component.bottomsheet

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
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine.Companion.fromLabel
import org.sopt.collaboration.campuspick.core.ui.model.FilterSection
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay.Companion.fromLabel
import org.sopt.collaboration.campuspick.core.ui.model.Region
import org.sopt.collaboration.campuspick.core.ui.model.Region.Companion.fromLabel
import org.sopt.collaboration.campuspick.feature.search.component.SearchKeyword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    showFilterBottomSheet: Boolean,
    onDismiss: (Boolean) -> Unit,
    navigateToAfterSearchWithBottomSheet: () -> Unit,
    bottomSheetDeadLineSelected: String,
    updateSelectedDeadLine: (DeadLine) -> Unit,
    bottomSheetRegionSelected: String,
    updateSelectedRegion: (Region) -> Unit,
    bottomSheetClubDaySelected: String,
    updateSelectedClubDay: (ClubDay) -> Unit,
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
                navigateToAfterSearchWithBottomSheet()
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
                            keywords = DeadLine.entries.filter {
                                it != DeadLine.EMPTY
                            }.map { it.label },
                            keywordType = { label ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetDeadLineSelected == label,
                                    onClick = {
                                        DeadLine.fromLabel(label).let(updateSelectedDeadLine)
                                    }
                                )
                            }
                        ),
                        FilterSection(
                            title = "지역",
                            keywords = Region.entries.filter {
                                it != Region.EMPTY
                            }.map { it.label },
                            keywordType = { label ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetRegionSelected == label,
                                    onClick = {
                                        Region.fromLabel(label).let(updateSelectedRegion)
                                    }
                                )
                            }
                        ),
                        FilterSection(
                            title = "활동 선호 요일",
                            keywords = ClubDay.entries.filter {
                                it != ClubDay.EMPTY
                            }.map { it.label },
                            keywordType = { label ->
                                FilterChip(
                                    buttonText = label,
                                    isSelected = bottomSheetClubDaySelected == label,
                                    onClick = {
                                        ClubDay.fromLabel(label).let(updateSelectedClubDay)
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