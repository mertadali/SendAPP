package com.mertadali.sendapp.presentation.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val emailState : String = "",
    val passwordState : String = "",
    val isLoadingState : Boolean = false,
    val errorMessageState : String? = null,
    val userState : FirebaseUser? = null,
    val isGoogleSignInClickedState : Boolean = false,      // Google SignIn yapılacak
    val isForgotPasswordClickedState : Boolean = false,
    val loggedInState : Boolean = false,
    val keepMeLoggedInState : Boolean = false

)
