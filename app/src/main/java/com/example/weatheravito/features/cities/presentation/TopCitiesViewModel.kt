package com.example.weatheravito.features.cities.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatheravito.features.cities.domain.CitiesInteractor
import com.example.weatheravito.features.cities.domain.model.ShortCity
import com.example.weatheravito.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopCitiesViewModel @Inject constructor(
    private val citiesInteractor: CitiesInteractor
) : ViewModel() {

    private val _cities = MutableLiveData<List<ShortCity>>()
    private val _search = MutableLiveData<List<ShortCity>>()
    private val _isLoading = MutableLiveData(true)
    val isLoading = _isLoading.asLiveData()
    val cities get() = _cities.asLiveData()
    val search get() = _search.asLiveData()

    init {
        _isLoading.value = true
        viewModelScope.launch {
            val topCities = citiesInteractor.getCity()
            _cities.value = topCities
            _isLoading.value = false
        }
    }

    fun onSearchQueryChanged(query: String, indicator: MutableLiveData<Boolean> = _isLoading) {
        indicator.value = true
        viewModelScope.launch {
            val searchCity = citiesInteractor.getSearchList(query)
            _search.value = searchCity
            indicator.value = false
        }
    }
}