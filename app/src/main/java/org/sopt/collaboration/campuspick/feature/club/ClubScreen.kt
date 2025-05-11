package org.sopt.collaboration.campuspick.feature.club

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ClubRoute(
    navigateToSearch: () -> Unit
) {
    ClubScreen(
        navigateToSearch = navigateToSearch
    )
}

@Composable
fun ClubScreen(
    navigateToSearch: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Club",
            modifier = Modifier.clickable {
                navigateToSearch()
            }
        )
    }
}