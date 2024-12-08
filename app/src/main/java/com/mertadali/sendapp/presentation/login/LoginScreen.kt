package com.mertadali.sendapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
                fontSize = 43.sp,
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
                        .padding(horizontal = 30.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier.padding(7.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Left,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = "E-mail",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "Enter your Email")


                    Text(
                        text = "Password",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialPasswordText(hint = "Enter Your Password")

                    Spacer(modifier = Modifier.padding(2.dp))


                    SpecialForgotPassword(onClick = { navController.navigate("forgot_screen") })


                    CheckBoxLoggedIn()

                    LoginButton(onClick = { navController.navigate("login_screen") })

                    OrLoginWith()
                    
                    GoogleSignInButton(onClick = { /*TODO*/ })

                    AskAccount(onClick = { /*TODO*/})


                    



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
        textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center, fontSize = 14.sp),

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RectangleShape)
            .height(49.dp)
            .onFocusChanged {
                // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 13.sp)} // Placeholder kullanımı
    )

}

@Composable
fun SpecialForgotPassword(onClick : () -> Unit){
    Text(
        text = "Forget password?",
        Modifier
            .clickable { onClick() }
            .padding(vertical = 3.dp, horizontal = 7.dp),
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 13.sp)
    
}

@Composable
fun SpecialPasswordText(hint: String){

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
        textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Start, fontSize = 14.sp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RectangleShape)
            .height(49.dp)
            .onFocusChanged {
                // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 13.sp)}// Placeholder kullanımı

    )

}

@Composable
fun CheckBoxLoggedIn(){

    var checked by remember {
        mutableStateOf(false)
    }

    val scaleMultiplier = 0.9f //scale by 2x

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()) {

        Checkbox(
            modifier = Modifier.scale(scaleMultiplier),
            checked = checked,
            onCheckedChange = {checked = it},
            colors = CheckboxDefaults.colors(checkedColor = Color.LightGray, uncheckedColor = Color.LightGray, checkmarkColor = Color.White))

        Text(text = "Keep me logged in.", color = Color.LightGray, fontSize = 13.sp)

    }

}

@Composable
fun LoginButton(onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 4.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(text ="Login", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold )

    }

}

@Composable
fun OrLoginWith() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.LightGray
        )

        Text(
            text = "or login with",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.LightGray
        )
    }
}

@Composable
fun GoogleSignInButton( onClick: () -> Unit){
     Box(contentAlignment = Alignment.Center) {

         Button(
             onClick = { onClick() },
             colors = ButtonDefaults.buttonColors(containerColor = Color.White),
             modifier = Modifier
                 .fillMaxWidth()
                 .height(48.dp)
                 .padding(horizontal = 42.dp),
             shape = RoundedCornerShape(6.dp)) {
             Image(
                 painter = painterResource(id = R.drawable.android_light_rd_s_),
                 contentDescription = "Google Logo",
                 modifier = Modifier
                     .fillMaxHeight()
                     .padding(horizontal = 16.dp))
         }
     }
}

@Composable
fun AskAccount(onClick: () -> Unit){
    Column(modifier = Modifier
        .padding(bottom = 29.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Don't you have an account yet?",
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            fontSize = 14.sp)

        Text(text = "Sign up",
            color = Color.Blue,
            modifier = Modifier.clickable { onClick() },
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline))
        
    }

}



