package com.hussein.weatherapp.domain.repository

import com.hussein.weatherapp.domain.util.Resource
import com.hussein.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat :Double , long:Double):Resource<WeatherInfo>
}