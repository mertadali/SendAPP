package com.mertadali.sendapp.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class LoginViewModel() {

    private val _state = mutableStateOf(value = LoginState())
    val state : State<LoginState> = _state



    fun onEvent(event: LoginEvent){

        when(event){
            is LoginEvent.EnterEmail -> {

            }
            LoginEvent.ClickForgot -> {

            }
            LoginEvent.ClickLogin -> {

            }
            is LoginEvent.EnterLoggedIn -> {

            }
            is LoginEvent.EnterPassword ->{

            }
            LoginEvent.SignGoogle -> {

            }
        }
    }
}