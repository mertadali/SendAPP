    package com.mertadali.sendapp.presentation.login

    sealed class LoginEvent {

        data class EnterEmail(val email : String) : LoginEvent()
        data class EnterPassword(val password : String) : LoginEvent()
        data object ClickLogin : LoginEvent()
        data object ClickForgot : LoginEvent()
        data object SignGoogle : LoginEvent()










    }