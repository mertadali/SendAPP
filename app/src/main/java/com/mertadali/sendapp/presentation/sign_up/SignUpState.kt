package com.mertadali.sendapp.presentation.sign_up

import com.google.firebase.auth.FirebaseUser

data class SignUpState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignUpSuccessful: Boolean = false,
    val errorMessage: String? = null,
    val user: FirebaseUser? = null,
    val isGoogleSignUpClicked: Boolean = false,
    val isPrivacyPolicyAccepted: Boolean = false)
