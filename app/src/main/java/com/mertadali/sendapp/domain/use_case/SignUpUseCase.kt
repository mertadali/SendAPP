package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(val repository : FirebaseAuthRepository) {

    suspend fun invoke(email: String, password: String) =
        repository.signUpWithEmailAndPassword(email, password)
}