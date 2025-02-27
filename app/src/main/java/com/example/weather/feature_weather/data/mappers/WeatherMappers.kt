package com.example.weather.feature_weather.data.mappers

import com.example.weather.feature_weather.data.remote.dto.WeatherDataDto
import com.example.weather.feature_weather.data.remote.dto.WeatherDto
import com.example.weather.feature_weather.domain.weather.WeatherData
import com.example.weather.feature_weather.domain.weather.WeatherInfo
import com.example.weather.feature_weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData,
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
//    val map = mutableMapOf<Int, List<WeatherData>>()
//    val list = mutableListOf<WeatherData>()
//    var key = 0
//
//    time.forEachIndexed { index, time ->
//        val temperature = temperatures[index]
//        val weatherCode = weatherCodes[index]
//        val windSpeed = windSpeeds[index]
//        val pressure = pressures[index]
//        val humidity = humidities[index]
//
//        val weatherData = WeatherData(
//            time = LocalDateTime.parse(
//                time, DateTimeFormatter.ISO_DATE_TIME
//            ),
//            temperatureCelsius = temperature,
//            windSpeed = windSpeed,
//            pressure = pressure,
//            humidity = humidity,
//            weatherType = WeatherType.fromWMO(weatherCode)
//        )
//
//        list.add(weatherData)
//
//        if (list.size == 24) {
//            map[key] = list
//            list.clear()
//            key++
//        }
//    }

//    if (list.isNotEmpty()) {
//        map[key] = list
//    }

    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(
                    time, DateTimeFormatter.ISO_DATE_TIME
                ),
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                pressure = pressure,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }
        .groupBy {
            it.index / 24
        }
        .mapValues {
            it.value.map { item ->
                item.data
            }
        }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = hourlyWeatherData.toWeatherDataMap()
    val now = LocalDateTime.now()

    val hour = if (now.minute < 30) {
        now.hour
    } else if (now.hour + 1 > 23) {
        0
    } else {
        now.hour + 1
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = weatherDataMap[0]?.find {
            hour == it.time.hour
        }
    )
}
