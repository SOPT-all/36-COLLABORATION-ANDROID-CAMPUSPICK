package org.sopt.collaboration.campuspick.core.ui.scroll

import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.scrollToTop() {
    animateScrollToItem(0)
}