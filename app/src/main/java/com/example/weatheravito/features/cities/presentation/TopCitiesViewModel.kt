package com.example.weatheravito.features.cities.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatheravito.features.cities.domain.CitiesInteractor
import com.example.weatheravito.features.cities.domain.model.ShortCity
import com.example.weatheravito.utils.ViewState
import com.example.weatheravito.utils.asLiveData
import com.example.weatheravito.utils.asViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopCitiesViewModel @Inject constructor(
    private val citiesInteractor: CitiesInteractor
) : ViewModel() {

    private val _cities = MutableLiveData<ViewState<List<ShortCity>>>()
    val cities get() = _cities.asLiveData()
    private val _search = MutableLiveData<ViewState<List<ShortCity>>>()
    val search get() = _search.asLiveData()
    private val _isLoading = MutableLiveData(true)
    val isLoading = _isLoading.asLiveData()
    private val _isRefresh = MutableLiveData(false)
    val isRefresh = _isRefresh.asLiveData()



    init {
        loadTopCities(_isLoading)
    }

    private fun loadTopCities(indicator: MutableLiveData<Boolean>){
        indicator.value = true
        viewModelScope.launch {
            val topCities = citiesInteractor.getCity()
            _cities.value = topCities.asViewState()
            indicator.value = false
        }
    }

    fun onSearchQueryChanged(query: String, indicator: MutableLiveData<Boolean> = _isLoading) {
        indicator.value = true
        viewModelScope.launch {
            val searchCity = citiesInteractor.getSearchList(query)
            _search.value = searchCity.asViewState()
            indicator.value = false
        }
    }

    fun onTopCitiesRefresh(){
        loadTopCities(_isRefresh)
    }
}