package com.mertadali.sendapp.presentation.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val isLoggedIn : Boolean = false,
    val user : FirebaseUser? = null
    )
