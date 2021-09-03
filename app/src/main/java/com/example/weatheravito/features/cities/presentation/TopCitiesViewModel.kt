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
    val cities get() = _cities.asLiveData()
    val search get() = _search.asLiveData()

    init {
        viewModelScope.launch {
            val topCities = citiesInteractor.getCity()
            _cities.value = topCities
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            val searchCity = citiesInteractor.getSearchList(query)
            _search.value = searchCity
        }
    }
}