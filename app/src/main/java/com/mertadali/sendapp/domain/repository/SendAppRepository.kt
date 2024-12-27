package com.mertadali.sendapp.domain.repository

import android.net.Uri
import com.mertadali.sendapp.data.remote.dto.WeatherDTO


interface SendAppRepository {

    // Api
    suspend fun getWeathersUseCase() : WeatherDTO

    // Room
    suspend fun addPlanRoomUseCase()
    suspend fun getPlanFromRoomUseCase()
    suspend fun saveProfileToRoomUseCase(fullName :String, email : String, job : String, imageUri : Uri)


    
}