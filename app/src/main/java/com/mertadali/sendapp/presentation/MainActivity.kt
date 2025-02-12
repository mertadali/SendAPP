    package com.mertadali.sendapp.presentation
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.material3.Surface
    import androidx.compose.ui.graphics.Color
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.mertadali.sendapp.presentation.feed.FeedScreen
    import com.mertadali.sendapp.presentation.forgot.ForgotScreen
    import com.mertadali.sendapp.presentation.login.LoginScreen
    import com.mertadali.sendapp.presentation.plans.add_plan.AddPlanScreen
    import com.mertadali.sendapp.presentation.plans.my_plans.MyPlansScreen
    import com.mertadali.sendapp.presentation.profile.ProfileScreen
    import com.mertadali.sendapp.presentation.sign_up.SignUpScreen
    import com.mertadali.sendapp.presentation.ui.theme.SendAppTheme
    import com.mertadali.sendapp.presentation.welcome.WelcomeScreen
    import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                SendAppTheme(darkTheme = false) {
                    Surface(color = Color.White) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Screen.WelcomeScreen.route
                        ) {

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
                                ForgotScreen(navController = navController)


                            }
                            composable(route = Screen.SignUpScreen.route) {
                                // SignUp Screen
                                SignUpScreen(navController = navController)

                            }
                            composable(route = Screen.FeedScreen.route) {
                                FeedScreen(navController = navController)
                                // Feed Screen

                            }
                            composable(route = Screen.ProfileScreen.route) {
                                // Profile Screen
                                ProfileScreen(navController = navController)
                            }
                            composable(route = Screen.MyPlansScreen.route) {
                                // My Plans Screen
                                MyPlansScreen(navController = navController)
                            }
                            composable(route = Screen.AddPlansScreen.route) {
                                // Add Plans Screen
                                AddPlanScreen(navController = navController)
                            }

                        }
                    }
                }

            }
        }
    }






