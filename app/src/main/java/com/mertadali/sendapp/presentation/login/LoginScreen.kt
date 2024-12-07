package com.mertadali.sendapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mertadali.sendapp.R

@Composable
fun LoginScreen(hint: String,navController: NavController){
   // val backgroundImage = painterResource(id = R.drawable.background)
    val underLogo = painterResource(id = R.drawable.logo)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)){

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .align(alignment = Alignment.Center)
            .background(color = Color.LightGray),
           horizontalAlignment = Alignment.CenterHorizontally) {


            Text(
                text = "Welcome",
                textAlign = TextAlign.Center,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                text = "to the",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive
            )

            Image(
                painter = underLogo, contentDescription = "logo",
                Modifier
                    .size(48.dp, 48.dp)
                    .clip(shape = CircleShape)
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            Card(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = Color.White)
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier.padding(7.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Left,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(vertical = 5.dp))

                    Text(
                        text = "E-mail",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "Enter your Email")

                    Spacer(modifier = Modifier.padding(vertical = 6.dp))

                    Text(
                        text = "Password",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "Enter your Email")



                    SpecialForgotPassword(onClick = { navController.navigate("forgot_screen") })


                }
            }
        }
   }
}

@Composable
fun SpecialTextField(hint: String){

    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp)
            .shadow(5.dp, CircleShape)
            .height(48.dp)
            .onFocusChanged {
                // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            })

}

@Composable
fun SpecialForgotPassword(onClick : () -> Unit){
    Text(
        text = "forgot password?",
        Modifier.clickable { onClick() },
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 15.sp)
    
}



