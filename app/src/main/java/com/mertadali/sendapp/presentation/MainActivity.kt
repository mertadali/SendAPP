package com.mertadali.sendapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertadali.sendapp.presentation.login.LoginScreen
import com.mertadali.sendapp.presentation.sign_up.SignUpScreen
import com.mertadali.sendapp.presentation.ui.theme.SendAppTheme
import com.mertadali.sendapp.presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SendAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Screen.WelcomeScreen.route) {

                        composable(route = Screen.WelcomeScreen.route) {
                            // Welcome Screen
                            WelcomeScreen(navController = navController)
                        }
                        composable(route = Screen.LoginScreen.route) {
                            // Login Screen
                            LoginScreen(navController)
                        }
                        composable(route = Screen.ForgotScreen.route) {
                            // Forgot Screen

                        }
                        composable(route = Screen.SignUpScreen.route) {
                            // SignUp Screen
                            SignUpScreen(navController = navController)

                        }
                        composable(route = Screen.FeedScreen.route) {
                            // Feed Screen

                        }
                        composable(route = Screen.ProfileScreen.route) {
                            // Profile Screen
                        }
                        composable(route = Screen.MyPlansScreen.route) {
                            // My Plans Screen
                        }
                        composable(route = Screen.AddPlansScreen.route) {
                            // Add Plans Screen
                        }
                    }
                }
            }
        }
    }
}
