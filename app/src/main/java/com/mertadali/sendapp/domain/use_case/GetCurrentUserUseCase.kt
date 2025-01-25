package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase  @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository){

    suspend operator fun invoke() = firebaseAuthRepository.getSignedInUser()
}