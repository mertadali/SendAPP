package com.mertadali.sendapp.data.dependency_injection

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mertadali.sendapp.R
import com.mertadali.sendapp.data.remote.WeatherApi
import com.mertadali.sendapp.data.repository.FirebaseRepoImpl
import com.mertadali.sendapp.data.repository.SendAppRepoImpl
import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import com.mertadali.sendapp.domain.repository.SendAppRepository
import com.mertadali.sendapp.domain.use_case.GoogleSignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SendAppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideSendRepo(api: WeatherApi): SendAppRepository {
        return SendAppRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepo(auth: FirebaseAuth): FirebaseAuthRepository = FirebaseRepoImpl(auth)

    @Provides
    @Singleton
    fun provideGoogleSignInUseCase(repository: FirebaseAuthRepository) =
        GoogleSignInUseCase(repository)

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext context: Context): GoogleSignInOptions {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return gso
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClientContext(
        @ApplicationContext context: Context,
        options: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(context, options)


    }
}