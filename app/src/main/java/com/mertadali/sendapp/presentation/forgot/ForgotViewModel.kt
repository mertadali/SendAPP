package com.mertadali.sendapp.presentation.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.sendapp.domain.use_case.ForgotPasswordUseCase
import com.mertadali.sendapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ForgotViewModel @Inject constructor(private val forgotPasswordUseCase: ForgotPasswordUseCase)  : ViewModel(){

    private val _statePassword = MutableStateFlow(ForgotPasswordState())
    val state: StateFlow<ForgotPasswordState> = _statePassword.asStateFlow()

    fun onEvent(event: ForgotEvent){
        when(event){
            is ForgotEvent.EnterEmail -> {
                _statePassword.value = _statePassword.value.copy(email = event.email)
            }
            is ForgotEvent.SendResetMail -> {
                SendResetEmail()
            }
        }
    }

    private fun SendResetEmail(){
        if (_statePassword.value.email.isEmpty()){
            _statePassword.value = _statePassword.value.copy(error = "Please enter your email ")
            return
        }
        viewModelScope.launch {
            forgotPasswordUseCase.invoke(_statePassword.value.email).collect{response ->
                when(response){
                   is Response.Success -> {
                       _statePassword.value = _statePassword.value.copy(isLoading = false, isEmailSent = true, error = null)

                   }
                    is Response.Error -> {
                        _statePassword.value = _statePassword.value.copy(isLoading = false, error = response.message)

                    }

                    is Response.Loading -> {
                        _statePassword.value = _statePassword.value.copy(isLoading = true, error = null)
                    }
                }
            }
        }

    }

}