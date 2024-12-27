package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = 
        repository.signInWithEmailAndPassword(email, password)
}