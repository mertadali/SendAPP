package com.mertadali.sendapp.presentation.login

data class LoginState(
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val isLoggedIn : Boolean = false
    )
