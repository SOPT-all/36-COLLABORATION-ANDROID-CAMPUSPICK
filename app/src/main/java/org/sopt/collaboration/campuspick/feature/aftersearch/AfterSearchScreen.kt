package org.sopt.collaboration.campuspick.feature.aftersearch

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun AfterSearchRoute(
    padding: PaddingValues,
    keyword: String?,
    category: String?,
    deadlineType: String?,
    region: String?,
    clubDay: String?,
    navigateToBack: () -> Unit,
    modifier: Modifier,
) {
    LaunchedEffectWithLifecycle(
        Log.d("asdasdasd", "$keyword $category $deadlineType $region $clubDay")
    )
    AfterSearchScreen()
}

@Composable
fun AfterSearchScreen() {
}