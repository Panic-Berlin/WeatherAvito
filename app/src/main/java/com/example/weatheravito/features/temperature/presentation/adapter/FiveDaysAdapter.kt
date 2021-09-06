package com.example.weatheravito.features.temperature.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatheravito.R
import com.example.weatheravito.databinding.FiveDaysItemBinding
import com.example.weatheravito.features.temperature.domain.model.DailyForecasts
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import org.joda.time.format.DateTimeFormat

class FiveDaysAdapter(
    val temperature: ShortTemperature
) : RecyclerView.Adapter<FiveDaysAdapter.FiveDaysViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiveDaysViewHolder {
        val cellForDaily =
            LayoutInflater.from(parent.context).inflate(R.layout.five_days_item, parent, false)
        return FiveDaysViewHolder(cellForDaily)
    }

    override fun onBindViewHolder(holder: FiveDaysViewHolder, position: Int) {
        holder.bind(temperature.dailyForecasts[position])
    }

    override fun getItemCount(): Int {
        return temperature.dailyForecasts.count()
    }

    class FiveDaysViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding: FiveDaysItemBinding by viewBinding(FiveDaysItemBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(temp: DailyForecasts) {
            viewBinding.dayMax.text = "${temp.temperature.maximum.valueInC}°"
            viewBinding.weakDay.text = temp.date.toString(DateTimeFormat.forPattern("E"))
            if  (temp.day.precipitationType == "Rain"){
                viewBinding.fiveDayItem.setBackgroundResource(R.drawable.rain_gradient_background)
                viewBinding.weatherIcon.setImageResource(R.drawable.ic_rain)
            }else{
                viewBinding.fiveDayItem.setBackgroundResource(R.drawable.days_item_background)
                viewBinding.weatherIcon.setImageResource(R.drawable.ic_sunny)
            }
        }
    }
}