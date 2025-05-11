package org.sopt.collaboration.campuspick.feature.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toPersistentList
import org.sopt.collaboration.campuspick.feature.main.component.MainBottomBar
import org.sopt.collaboration.campuspick.feature.main.component.MainNavHost

@Composable
internal fun MainScreen(
    modifier: Modifier,
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding,
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
                visible = navigator.setBottomBarVisibility(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}