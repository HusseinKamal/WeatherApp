package com.hussein.weatherapp.presentation

import com.hussein.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo :WeatherInfo? = null,
    val isLoading:Boolean = false,
    val error:String? = "",
)
