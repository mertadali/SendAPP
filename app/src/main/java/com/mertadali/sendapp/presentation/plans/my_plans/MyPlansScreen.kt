package com.mertadali.sendapp.presentation.plans.my_plans

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mertadali.sendapp.presentation.Screen
import com.mertadali.sendapp.presentation.feed.BottomNavBar

@Composable
fun MyPlansScreen(navController: NavController){


    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = listOf(
                    Screen.FeedScreen,
                    Screen.AddPlansScreen,
                    Screen.MyPlansScreen,
                    Screen.ProfileScreen
                )
            )
        }
    ) { innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }

    }
    


}