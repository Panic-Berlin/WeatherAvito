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

    fun getDailyWeather(key: String, indicator: MutableLiveData<Boolean> = _isLoading) {
        indicator.value = true
        viewModelScope.launch {
            val temp = temperatureInteractor.getDaily(key)
            if (temp != null) {
                _weatherDaily.value = temp
                indicator.value = false
            }
        }
    }

    fun getFiveDaiWeather(key: String) {
        viewModelScope.launch {
            val temp = temperatureInteractor.getFiveDay(key)
            if (temp != null) {
                _weatherFiveDay.value = temp
            }
        }
    }
}