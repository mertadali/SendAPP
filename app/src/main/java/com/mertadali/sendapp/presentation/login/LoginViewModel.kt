package com.mertadali.sendapp.presentation.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.sendapp.domain.use_case.GetCurrentUserUseCase
import com.mertadali.sendapp.domain.use_case.GoogleSignInUseCase
import com.mertadali.sendapp.domain.use_case.SetLoggedInUseCase
import com.mertadali.sendapp.domain.use_case.SignInUseCase
import com.mertadali.sendapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val setLoggedInUseCase: SetLoggedInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                val currentUser = getCurrentUserUseCase()
                val isLoggedIn = setLoggedInUseCase.invokeIsLoggedIn()

                _state.value = _state.value.copy(
                    loggedInState = currentUser != null && isLoggedIn,
                    keepMeLoggedInState = isLoggedIn,
                    userState = if (isLoggedIn) currentUser else null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    errorMessageState = e.message ?: "Failed to check login state",
                    loggedInState = false,
                    keepMeLoggedInState = false
                )
            }
        }
    }


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnterEmail -> {
                _state.value = _state.value.copy(emailState = event.email)

            }

            is LoginEvent.EnterPassword -> {
                _state.value = _state.value.copy(passwordState = event.password)

            }


            is LoginEvent.ClickLogin -> {
                login(_state.value.emailState, _state.value.passwordState)
            }

            LoginEvent.ClickForgot -> {
                _state.value = _state.value.copy(isForgotPasswordClickedState = true)

            }

            LoginEvent.SignGoogle -> {
                // Google SignIn yapılacak !!
                _state.value = _state.value.copy(isGoogleSignInClickedState = true)

            }

            is LoginEvent.IsLoggedIn -> {
                _state.value = _state.value.copy(keepMeLoggedInState = event.clicked)

            }
        }
    }


    private fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _state.value = _state.value.copy(errorMessageState = "Email or Password is empty")
            return
        }
        viewModelScope.launch {

            signInUseCase.invoke(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                       _state.value = _state.value.copy(isLoadingState = false, errorMessageState = response.message, loggedInState = false)
                    }

                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            isLoadingState = true,
                            errorMessageState = null
                        )
                    }

                    is Response.Success -> {
                        if (response.data.user != null) {
                            _state.value = _state.value.copy(
                                isLoadingState = false,
                                userState = response.data.user,
                                errorMessageState = null
                            )

                            if (_state.value.keepMeLoggedInState) {
                                setLoggedInUseCase.invokeSetLoggedIn(true)
                            }
                            _state.value = _state.value.copy(loggedInState = true)
                        } else {
                            _state.value = _state.value.copy(
                                isLoadingState = false,
                                errorMessageState = "Authentication failed",
                                loggedInState = false
                            )
                        }
                    }

                }
            }
        }
    }


    fun handleGoogleSignIn(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingState = true)
            try {
                googleSignInUseCase(idToken).collect { response ->
                    when (response) {
                        is Response.Success -> {
                            if (response.data.user != null) {
                                _state.value = _state.value.copy(
                                    isLoadingState = false,
                                    userState = response.data.user,
                                    errorMessageState = null
                                )

                                if (_state.value.keepMeLoggedInState) {
                                    setLoggedInUseCase.invokeSetLoggedIn(true)
                                }
                                _state.value = _state.value.copy(loggedInState = true)
                            } else {
                                _state.value = _state.value.copy(
                                    isLoadingState = false,
                                    errorMessageState = "Google authentication failed",
                                    loggedInState = false
                                )
                            }
                        }

                        is Response.Error -> {
                            _state.value = _state.value.copy(
                                isLoadingState = false,
                                errorMessageState = response.message,
                                loggedInState = false
                            )
                        }

                        is Response.Loading -> {
                            _state.value = _state.value.copy(isLoadingState = true)
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoadingState = false,
                    errorMessageState = e.message ?: "Google sign in failed",
                    loggedInState = false
                )
            }
        }
    }
}



