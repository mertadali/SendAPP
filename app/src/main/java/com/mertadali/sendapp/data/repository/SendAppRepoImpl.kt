package com.mertadali.sendapp.data.repository


import android.net.Uri
import com.mertadali.sendapp.data.remote.WeatherApi
import com.mertadali.sendapp.data.remote.dto.WeatherDTO
import com.mertadali.sendapp.domain.repository.SendAppRepository
import javax.inject.Inject

class SendAppRepoImpl @Inject constructor(
    private val api : WeatherApi)  : SendAppRepository {
    override suspend fun getWeathersUseCase(): WeatherDTO {
        TODO("Not yet implemented")
    }

    override suspend fun addPlanRoomUseCase() {
        TODO("Not yet implemented")
    }

    override suspend fun getPlanFromRoomUseCase() {
        TODO("Not yet implemented")
    }

    override suspend fun saveProfileToRoomUseCase(
        fullName: String,
        email: String,
        job: String,
        imageUri: Uri
    ) {
        TODO("Not yet implemented")
    }
}