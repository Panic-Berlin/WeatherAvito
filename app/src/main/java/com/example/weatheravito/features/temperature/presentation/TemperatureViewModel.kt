package com.example.weatheravito.features.temperature.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatheravito.features.temperature.domain.TemperatureInteractor
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import com.example.weatheravito.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val temperatureInteractor: TemperatureInteractor
) : ViewModel() {

    private val _weatherDaily = MutableLiveData<ShortTemperature>()
    val weatherDaily get() = _weatherDaily.asLiveData()

    private val _isLoading = MutableLiveData(true)
    val isLoading = _isLoading.asLiveData()

    private val _weatherFiveDay = MutableLiveData<ShortTemperature>()
    val weatherFiveDay get() = _weatherFiveDay.asLiveData()

    fun getDailyWeather(key: String) {
        viewModelScope.launch {
            val temp = temperatureInteractor.getDaily(key)
            _weatherDaily.value = temp
        }
    }

    fun getFiveDaiWeather(key: String) {
        viewModelScope.launch {
            val temp = temperatureInteractor.getFiveDay(key)
            _weatherFiveDay.value = temp
        }
    }
}