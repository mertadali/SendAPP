package com.mertadali.sendapp.data.remote.dto

import com.mertadali.sendapp.domain.model.DomainWeather
data class WeatherDTO(

    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherDTO.useWeather() : DomainWeather{
    val weatherItem = weather.firstOrNull() // Listeden ilk hava durumu verisini al
    return DomainWeather(
        cityName = name,
        latitude = coord.lat,
        longitude = coord.lon,
        temperature = main.temp,
        tempMin = main.temp_min,
        tempMax = main.temp_max,
        description = weatherItem?.description ?: "No valid data",
        iconUrl = "https://openweathermap.org/img/wn/${weatherItem?.icon}.png",
        weatherMain = weatherItem?.main ?: "Unknown",
        weatherId = weatherItem?.id ?: 0)


}


