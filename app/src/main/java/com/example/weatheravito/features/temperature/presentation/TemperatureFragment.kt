package com.example.weatheravito.features.temperature.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatheravito.R
import com.example.weatheravito.databinding.FragmentTemperatureBinding
import com.example.weatheravito.features.cities.domain.model.ShortCity
import com.example.weatheravito.features.temperature.presentation.adapter.FiveDaysAdapter
import com.example.weatheravito.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.format.DateTimeFormat

@AndroidEntryPoint
class TemperatureFragment : Fragment(R.layout.fragment_temperature) {

    private val viewBinding: FragmentTemperatureBinding by viewBinding(FragmentTemperatureBinding::bind)
    private val viewModel: TemperatureViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable("key") as ShortCity).let { city ->
            viewModel.onArgsReceived(city)
        }

        viewBinding.fiveDayRV.addItemDecoration(
            DayWeatherItemDecoration(
                requireContext(),
                R.dimen.day_weather_spacing,
                R.dimen.day_weather_top_spacing,
                R.dimen.day_weather_bottom_spacing
            )
        )

        observeDailyWeather()
        observeFiveDayWeather()
        observeCity()
        observeLoading()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            viewBinding.tempLoading.isVisible = it
        }
    }

    private fun observeCity() {
        viewModel.city.observe(viewLifecycleOwner) {
            viewBinding.city.text = it.localizedName
        }
    }

    private fun observeDailyWeather() {
        viewModel.weatherDaily.observe(viewLifecycleOwner, {
            viewBinding.temperatureCV.isVisible = it is ViewState.Show
            viewBinding.city.isVisible = it is ViewState.Show
            viewBinding.weatherStatusIcon.isVisible = it is ViewState.Show
            viewBinding.tvTempError.isVisible = it is ViewState.Error
            if (it is ViewState.Show) {
                it.data.dailyForecasts.firstOrNull()?.let { dailyForecast ->
                    viewBinding.temperature.text = getString(
                        R.string.temperature_in_c,
                        dailyForecast.temperature.maximum.valueInC
                    )
                    viewBinding.status.text = dailyForecast.day.iconPhrase
                    viewBinding.dateLocalTime.text =
                        dailyForecast.date.toString(
                            DateTimeFormat.forPattern("EEEE HH:mm a")
                        )
                    if (dailyForecast.day.precipitationType == "Rain") {
                        viewBinding.weatherStatusIcon.setImageResource(R.drawable.ic_rain)
                    } else {
                        viewBinding.weatherStatusIcon.setImageResource(R.drawable.ic_sunny)
                    }
                }
            }
        })
    }

    private fun observeFiveDayWeather() {
        val fiveDaysRecyclerView = viewBinding.fiveDayRV
        fiveDaysRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.weatherFiveDay.observe(viewLifecycleOwner, {
            viewBinding.fiveDayRV.isVisible = it is ViewState.Show
            viewBinding.days.isVisible = it is ViewState.Show
            if (it is ViewState.Show) {
                val dailyAdapter = FiveDaysAdapter(it.data){ it ->
                    viewBinding.temperature.text = getString(
                        R.string.temperature_in_c,

                        it.temperature.maximum.valueInC
                    )
                    viewBinding.status.text = it.day.iconPhrase
                    viewBinding.dateLocalTime.text =
                        it.date.toString(
                            DateTimeFormat.forPattern("EEEE HH:mm a")
                        )
                    if (it.day.precipitationType == "Rain") {
                        viewBinding.weatherStatusIcon.setImageResource(R.drawable.ic_rain)
                    } else {
                        viewBinding.weatherStatusIcon.setImageResource(R.drawable.ic_sunny)
                    }
                }
                fiveDaysRecyclerView.adapter = dailyAdapter
            }
        })
    }
}
