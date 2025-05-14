package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel

class SearchViewModel() : BaseViewModel<SearchState, SearchSideEffect>(SearchState()) {

    fun updateInputSearch(inputSearch: String) {
        intent {
            copy(
                inputSearch = inputSearch
            )
        }
    }
}