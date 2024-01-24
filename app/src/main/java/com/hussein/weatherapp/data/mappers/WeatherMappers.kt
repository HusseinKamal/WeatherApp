package com.hussein.weatherapp.data.mappers

import com.hussein.weatherapp.data.remote.WeatherDataDto
import com.hussein.weatherapp.data.remote.WeatherDto
import com.hussein.weatherapp.domain.weather.WeatherData
import com.hussein.weatherapp.domain.weather.WeatherInfo
import com.hussein.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index :Int,
    val data : WeatherData
)

fun WeatherDataDto.toWeatherDataMAp():Map<Int,List<WeatherData>>{
    return time.mapIndexed{index,time ->
        val temperature =temperatures[index]
        val weatherCode =weatherCodes[index]
        val windSpeed =windSpeeds[index]
        val pressure =pressures[index]
        val humidity =humidities[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure =  pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )

    }.groupBy {
        it.index / 24 //per day
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo():WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMAp()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30 ) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        weatherDataPerDay = weatherDataMap
    )
}