package com.mertadali.sendapp.presentation

sealed class Screen(val route : String){
    object WelcomeScreen : Screen("welcome_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object FeedScreen : Screen("feed_screen")
    object ProfileScreen : Screen("profile_screen")
    object MyPlansScreen : Screen("myPlans_screen")
    object AddPlansScreen : Screen("addPlans_screen")


}