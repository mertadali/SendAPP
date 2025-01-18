package com.mertadali.sendapp.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.sendapp.domain.use_case.GoogleSignInUseCase
import com.mertadali.sendapp.domain.use_case.SignUpUseCase
import com.mertadali.sendapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, val googleSignInUseCase: GoogleSignInUseCase) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()



    // Email validasyonu için regex pattern
    private val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|edu|gov|info|tr)\$"
    private fun validateSignUpData(email: String, password: String, fullName: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            _state.value = _state.value.copy(errorMessage = "Email, password and name fields cannot be empty")
            return false
        }

        // Email format kontrolü
        if (!email.lowercase().matches(emailPattern.toRegex())) {
            _state.value = _state.value.copy(errorMessage = "Please enter a valid email address (e.g., example@domain.com)")
        }

        // Password kontrolü
        if (password.length < 6) {
            _state.value = _state.value.copy(errorMessage = "Password must be at least 6 characters")
            return false
        }

        // İsim kontrolü
        if (fullName.length < 3) {
            _state.value = _state.value.copy(errorMessage = "Name must be at least 3 characters")
            return false
        }

        if (!fullName.matches("[a-zA-Z ]+".toRegex())) {
            _state.value = _state.value.copy(errorMessage = "Name can only contain letters")
            return false
        }

        return true
    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUpClicked -> {
                signUp(_state.value.email, _state.value.password, _state.value.fullName)

            }

            is SignUpEvent.EnterEmail -> {
                _state.value = _state.value.copy(email = event.email, errorMessage = null)

            }

            is SignUpEvent.EnterFullName -> {
                _state.value = _state.value.copy(fullName = event.fullName, errorMessage = null)

            }

            is SignUpEvent.EnterPassword -> {
                _state.value = _state.value.copy(password = event.password, errorMessage = null)

            }

            is SignUpEvent.GoogleSignUpClicked -> {
                _state.value = _state.value.copy(isGoogleSignUpClicked = true)

            }

            is SignUpEvent.PrivacyPolicyCheckboxClicked -> {
                _state.value = _state.value.copy(isPrivacyPolicyAccepted = event.isChecked)

            }
        }
    }

    private fun signUp(email: String, password: String, fullName: String) {
        if (!validateSignUpData(email, password, fullName)) {
            return
        }

        if (!_state.value.isPrivacyPolicyAccepted) {
            _state.value = _state.value.copy(errorMessage = "Please accept the privacy policy")
            return
        }

        viewModelScope.launch {
            signUpUseCase.invoke(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                        _state.value =
                            _state.value.copy(isLoading = false, errorMessage = response.message)

                    }

                    is Response.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, errorMessage = null)
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isSignUpSuccessful = true,
                            user = response.data.user,
                            errorMessage = null
                        )
                    }
                }
            }
        }


    }
    fun handleGoogleSignUp(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                googleSignInUseCase(idToken).collect { response ->

                    when(response) {
                        is Response.Success -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                isSignUpSuccessful = true,
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


