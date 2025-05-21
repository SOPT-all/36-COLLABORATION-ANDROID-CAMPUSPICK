package org.sopt.collaboration.campuspick.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.collaboration.campuspick.data.api.ApiFactory
import org.sopt.collaboration.campuspick.data.repository.CampusPickRepositoryImpl
import org.sopt.collaboration.campuspick.feature.aftersearch.AfterSearchViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            AfterSearchViewModel::class.java -> {
                AfterSearchViewModel(
                    CampusPickRepositoryImpl(ApiFactory.ServicePool.campusPickService)
                ) as T

            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}