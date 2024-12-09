package com.mertadali.sendapp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mertadali.sendapp.R

@Composable
fun WelcomeScreen(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.background2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // Background image

        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(vertical = 50.dp)
                .fillMaxSize()
        ) {

           Text(
                text = "Welcome to SendApp",
                modifier = Modifier.fillMaxWidth(),
               textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                fontSize = 49.sp,
                lineHeight = 54.sp

            )

            Spacer(modifier = Modifier.padding(9.dp))

            Text(
                text = "Share Your Plans, Discover Ideas, Build Connections!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default,
                fontSize = 18.sp

            )


            Spacer(modifier = Modifier.padding(vertical = 150.dp))


            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .align(alignment =Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .align(alignment = Alignment.End)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {

                    Button(
                        onClick = { navController.navigate(route = "login_screen") },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 13.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = "Login",
                            style = TextStyle(color = Color.Black, fontSize = 17.sp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))

                Row (modifier = Modifier.fillMaxWidth()){
                    Button(
                        onClick = { navController.navigate(route = "signup_screen") },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 13.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = "Sign Up",
                            style = TextStyle(color = Color.Black, fontSize = 17.sp),
                            fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}


