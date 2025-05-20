package org.sopt.collaboration.campuspick.core.designsystem.component.tabrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.collaboration.campuspick.core.designsystem.component.button.CategoryButton

@Composable
fun ClubCategoryTabRow(
    tabTitles: ImmutableList<String>,
    onTabClick: (Int) -> Unit,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterHorizontally
            ),
        contentPadding = PaddingValues(horizontal = 15.dp),
        modifier = modifier
            .fillMaxWidth()
        ,
    ) {
        itemsIndexed(
            items = tabTitles,
            key = { index, title -> title },
        ) { index, title ->
            CategoryButton(
                buttonText = title,
                onClick = { onTabClick(index) },
                isSelected = index == selectedIndex
            )
        }
    }
}