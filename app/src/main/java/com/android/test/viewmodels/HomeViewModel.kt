package com.android.test.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.test.network.Resource
import com.android.test.network.models.HomeDataModelItem
import com.android.test.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _homeResponse = MutableStateFlow<Resource<List<HomeDataModelItem>>>(Resource.Empty)
    val getHomeDataResponse: StateFlow<Resource<List<HomeDataModelItem>>> = _homeResponse


    fun homeDataRequest() = viewModelScope.launch {
        _homeResponse.value = Resource.Loading
        _homeResponse.value = homeRepository.getHomeResponse()
    }

}