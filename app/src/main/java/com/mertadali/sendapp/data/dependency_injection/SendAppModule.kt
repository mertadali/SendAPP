package com.mertadali.sendapp.data.dependency_injection

import com.google.firebase.auth.FirebaseAuth
import com.mertadali.sendapp.data.remote.WeatherApi
import com.mertadali.sendapp.data.repository.FirebaseRepoImpl
import com.mertadali.sendapp.data.repository.SendAppRepoImpl
import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import com.mertadali.sendapp.domain.repository.SendAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SendAppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideSendRepo(api : WeatherApi ) : SendAppRepository{
        return SendAppRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepo(auth: FirebaseAuth) : FirebaseAuthRepository = FirebaseRepoImpl(auth)



}