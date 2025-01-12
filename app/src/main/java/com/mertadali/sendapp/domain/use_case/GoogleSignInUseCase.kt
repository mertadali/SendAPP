package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(val repository: FirebaseAuthRepository) {
    suspend operator fun invoke(token : String) =  repository.signInWithGoogle(token)
}