package com.mertadali.sendapp.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.sendapp.domain.use_case.SignUpUseCase
import com.mertadali.sendapp.presentation.login.LoginState
import com.mertadali.sendapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel(){
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.SignUpClicked ->{
                signUp(_state.value.email, _state.value.password, _state.value.fullName)

            }
            is SignUpEvent.EnterEmail ->{
                _state.value = _state.value.copy(email = event.email)

            }
            is SignUpEvent.EnterFullName -> {
                _state.value = _state.value.copy(fullName = event.fullName)

            }
            is SignUpEvent.EnterPassword -> {
                _state.value = _state.value.copy(password = event.password)

            }
            is SignUpEvent.GoogleSignUpClicked -> {
                _state.value = _state.value.copy(isGoogleSignUpClicked = true)

            }
            is SignUpEvent.PrivacyPolicyCheckboxClicked ->{
                _state.value = _state.value.copy(isPrivacyPolicyAccepted = true)

            }
        }
    }

    fun signUp(email : String, password : String,fullName: String){
        if(email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            _state.value = _state.value.copy(errorMessage = "Email or Password, name is empty")
            return
        }
        viewModelScope.launch {
            signUpUseCase.invoke(email, password).collect{ response ->
                when(response){
                    is Response.Error -> {
                        _state.value = _state.value.copy(isLoading = false, errorMessage = response.message)

                    }
                     is Response.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, errorMessage = null)
                    }
                    is Response.Success -> {
                        _state.value = _state.value.copy(isLoading = false, isSignUpSuccessful = true)
                    }
                }
            }
        }
    }
}