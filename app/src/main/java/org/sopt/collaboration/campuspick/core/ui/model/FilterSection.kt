package org.sopt.collaboration.campuspick.core.ui.model

import androidx.compose.runtime.Composable

data class FilterSection(
    val title: String,
    val keywords: List<String>,
    val keywordType: @Composable (String) -> Unit
)