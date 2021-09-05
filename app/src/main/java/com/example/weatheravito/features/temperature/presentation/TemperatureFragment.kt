package com.example.weatheravito.features.temperature.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatheravito.R
import com.example.weatheravito.databinding.FragmentTemperatureBinding
import com.example.weatheravito.features.cities.domain.model.ShortCity
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import com.example.weatheravito.features.temperature.presentation.adapter.FiveDaysAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemperatureFragment : Fragment(R.layout.fragment_temperature) {


    private val viewBinding: FragmentTemperatureBinding by viewBinding(FragmentTemperatureBinding::bind)
    private val viewModel: TemperatureViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable("key") as ShortCity)?.let { city ->
            getDailyWeather(city.key, city.localizedName)
            getFiveDaysWeather(city.key)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getDailyWeather(key: String, city: String) {
        viewModel.getDailyWeather(key)
        viewBinding.city.text = city
        viewModel.weatherDaily.observe(viewLifecycleOwner, {
            viewBinding.temperature.text =
                "${it.dailyForecasts.firstOrNull()?.temperature?.maximum?.valueInC.toString()} °C"
            viewBinding.status.text = it.dailyForecasts.firstOrNull()?.day?.iconPhrase
        })

    }

    private fun getFiveDaysWeather(key: String) {
        val fiveDaysRecyclerView = viewBinding.fiveDayRV
        fiveDaysRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getFiveDaiWeather(key)
        viewModel.weatherFiveDay.observe(viewLifecycleOwner, {
            val dailyAdapter = FiveDaysAdapter(it)
            fiveDaysRecyclerView.adapter = dailyAdapter
        })
    }
}