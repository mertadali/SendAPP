package com.mertadali.sendapp.data.remote

import com.mertadali.sendapp.data.remote.dto.WeatherDTO
import com.mertadali.sendapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

    /*
    // coord.lon Longitude of the location ,    coord.lat Latitude of the location

     */

    @GET("weather")
    suspend fun getCurrentWeatherFromApi(
        @Query("lat") latitude: Double,
        @Query("lon") longitude : Double,
        @Query("appid") apiKey : String = API_KEY,
        @Query("units") units : String = "metric"
    ): WeatherDTO


}