package org.sopt.collaboration.campuspick.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.collaboration.campuspick.data.api.ApiFactory
import org.sopt.collaboration.campuspick.data.repository.CampusPickRepositoryImpl
import org.sopt.collaboration.campuspick.feature.club.ClubViewModel
import org.sopt.collaboration.campuspick.feature.aftersearch.AfterSearchViewModel
import org.sopt.collaboration.campuspick.feature.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            HomeViewModel::class.java -> {
                HomeViewModel(
                    CampusPickRepositoryImpl(ApiFactory.ServicePool.campusPickService)
                ) as T
            }

            ClubViewModel::class.java -> {
                ClubViewModel(
                    CampusPickRepositoryImpl(ApiFactory.ServicePool.campusPickService)
                ) as T
            }

            AfterSearchViewModel::class.java -> {
                AfterSearchViewModel(
                    CampusPickRepositoryImpl(ApiFactory.ServicePool.campusPickService)
                ) as T

            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}