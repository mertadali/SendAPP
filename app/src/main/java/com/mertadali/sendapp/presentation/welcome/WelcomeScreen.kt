package com.mertadali.sendapp.presentation.welcome


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.mertadali.sendapp.R

@Composable
fun WelcomeScreen(navController: NavController){
    val backgroundImage = painterResource(id = R.drawable.background)
    
    val backgroundImageLogo = painterResource(id = R.drawable.logo)
 
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)){


        
    }

}