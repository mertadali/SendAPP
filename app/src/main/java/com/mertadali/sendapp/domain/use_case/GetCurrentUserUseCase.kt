package com.mertadali.sendapp.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase  @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository){

    suspend operator fun invoke() : FirebaseUser? = firebaseAuthRepository.getSignedInUser()
}