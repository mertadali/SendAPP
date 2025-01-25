package com.mertadali.sendapp.domain.use_case

import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class SetLoggedInUseCase @Inject constructor(private val repository: FirebaseAuthRepository){
    suspend operator  fun invoke(loggedIn: Boolean) = repository.setLoggedIn(loggedIn)

}