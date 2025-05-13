package org.sopt.collaboration.campuspick.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable

@Composable
fun HomeRoute(
    navigateToClub: () -> Unit
) {
    HomeScreen(
        navigateToClub = navigateToClub
    )
}

@Composable
fun HomeScreen(
    navigateToClub: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Home",
            modifier = Modifier.customClickable(
                rippleEnabled = false,
                singleClick = true
            ) {
                navigateToClub()
            }
        )
    }
}