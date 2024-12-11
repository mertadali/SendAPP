package com.mertadali.sendapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route : String ,val icon: ImageVector? = null){
    data object WelcomeScreen : Screen("welcome_screen")
    data object ForgotScreen : Screen("forgot_screen")
    data object LoginScreen : Screen("login_screen")
    data object SignUpScreen : Screen("signup_screen")
    data object FeedScreen : Screen("feed_screen",Icons.Filled.Home)
    data object ProfileScreen : Screen("profile_screen",Icons.Filled.Person)
    data object MyPlansScreen : Screen("myPlans_screen", Icons.AutoMirrored.Filled.List)
    data object AddPlansScreen : Screen("addPlans_screen",Icons.Filled.Add)


}
