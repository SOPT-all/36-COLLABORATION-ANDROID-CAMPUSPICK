package org.sopt.collaboration.campuspick.core.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun getImageResId(imageNameFromServer: String): Int {
    val context = LocalContext.current
    val resourceName = "img_${imageNameFromServer.lowercase()}"

    return remember(imageNameFromServer) {
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
}