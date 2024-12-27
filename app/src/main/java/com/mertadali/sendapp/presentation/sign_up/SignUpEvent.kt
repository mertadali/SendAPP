package com.mertadali.sendapp.presentation.sign_up

sealed class SignUpEvent {
    data class EnterFullName(val fullName: String) : SignUpEvent()
    data class EnterEmail(val email: String) : SignUpEvent()
    data class EnterPassword(val password: String) : SignUpEvent()
    data class PrivacyPolicyCheckboxClicked(val isChecked: Boolean) : SignUpEvent()
    data object SignUpClicked : SignUpEvent()
    data object GoogleSignUpClicked : SignUpEvent()
}