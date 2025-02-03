package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

/**
 * Domain katmanında Remember Me özelliği için use case.
 * Kullanıcının oturum durumunu kalıcı olarak saklamak ve kontrol etmek için kullanılır.
 * Repository üzerinden veri katmanı ile iletişim kurar.


        Kullanıcının oturum durumunu kalıcı olarak saklar
        @param loggedIn Oturum durumu (true/false)

        Kaydedilmiş oturum durumunu kontrol eder
       @return Oturum durumu (true/false)

 */
class SetLoggedInUseCase @Inject constructor(private val repository: FirebaseAuthRepository){

    suspend  fun invokeSetLoggedIn(loggedIn: Boolean) = repository.setLoggedIn(loggedIn)


    suspend fun invokeIsLoggedIn() = repository.getLoggedIn()
}