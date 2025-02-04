package com.mertadali.sendapp.presentation.forgot

data class ForgotPasswordState(
    val email : String ="",
    val isLoading : Boolean = false,
    val error  : String? = null,
    val isEmailSent : Boolean = false

)
