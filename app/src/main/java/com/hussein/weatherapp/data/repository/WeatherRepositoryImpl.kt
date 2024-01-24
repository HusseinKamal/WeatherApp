package com.hussein.weatherapp.data.repository

import com.hussein.weatherapp.data.mappers.toWeatherInfo
import com.hussein.weatherapp.data.remote.WeatherApi
import com.hussein.weatherapp.domain.repository.WeatherRepository
import com.hussein.weatherapp.domain.util.Resource
import com.hussein.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api : WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeather(
                    lat = lat,
                    lng = long
                ).toWeatherInfo()
            )
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message?: "An unknown error occured")
        }
    }
}