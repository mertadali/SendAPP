package com.mertadali.sendapp.domain.repository

import com.mertadali.sendapp.data.remote.dto.WeatherDTO

interface SendAppRepository {

    suspend fun getWeather(cityName : String) : WeatherDTO
}