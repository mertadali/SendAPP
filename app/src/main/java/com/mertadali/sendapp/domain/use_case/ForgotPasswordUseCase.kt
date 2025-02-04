package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val repository : FirebaseAuthRepository){
    suspend operator fun invoke(email : String) = repository.sendPasswordResetEmail(email)
}



