package com.mertadali.sendapp.presentation.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.sendapp.domain.use_case.GoogleSignInUseCase
import com.mertadali.sendapp.domain.use_case.SignInUseCase
import com.mertadali.sendapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val signInUseCase: SignInUseCase, private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EnterEmail -> {
                _state.value = _state.value.copy(email = event.email)

            }
            is LoginEvent.EnterPassword ->{
                _state.value = _state.value.copy(password = event.password)

            }


           is  LoginEvent.ClickLogin -> {
                login(_state.value.email, _state.value.password)
            }

            LoginEvent.ClickForgot -> {
                _state.value = _state.value.copy(isForgotPasswordClicked = true)

            }

            LoginEvent.SignGoogle -> {
                // Google SignIn yapılacak !!
                _state.value = _state.value.copy(isGoogleSignInClicked = true)

            }

        }
    }

     fun login(email : String, password : String){
         if(email.isEmpty() || password.isEmpty()) {
             _state.value = _state.value.copy(errorMessage = "Email or Password is empty")
             return
         }
        viewModelScope.launch {
            signInUseCase.invoke(email, password).collect{ response ->
                when(response){
                    is Response.Error -> {
                        _state.value = _state.value.copy(isLoading = false, errorMessage = response.message)

                    }
                   is  Response.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, errorMessage = null)
                    }
                    is Response.Success -> {
                        _state.value = _state.value.copy(isLoading = false, isLoggedIn = true)
                    }
                }
            }
        }
    }

    fun handleGoogleSignIn(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                googleSignInUseCase(idToken).collect { response ->

                    when(response) {
                        is Response.Success -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                isLoggedIn = true,
                                user = response.data.user
                            )
                        }
                        is Response.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorMessage = response.message
                            )
                        }
                        is Response.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Google sign in failed"
                )
            }
        }
    }
}