package com.mertadali.sendapp.domain.model


data class DomainWeather(
    val cityName: String, // Şehir adı
    val latitude: Double, // Enlem
    val longitude: Double, // Boylam
    val temperature: Double, // Sıcaklık
    val tempMin: Double, // Min sıcaklık
    val tempMax: Double, // Max sıcaklık
    val description: String, // Hava durumu açıklaması
    val iconUrl: String, // Hava durumu ikonu
    val weatherMain : String,
    val weatherId : Int
)
