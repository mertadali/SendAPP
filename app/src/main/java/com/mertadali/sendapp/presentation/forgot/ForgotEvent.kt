package com.mertadali.sendapp.presentation.forgot

sealed class ForgotEvent  {
    data class EnterEmail(val email : String) : ForgotEvent()    // neden data class veya object öğren.
    data object SendResetMail : ForgotEvent()
}