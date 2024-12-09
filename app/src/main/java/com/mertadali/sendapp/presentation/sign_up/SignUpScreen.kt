package com.mertadali.sendapp.presentation.sign_up

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mertadali.sendapp.R

@Composable
fun SignUpScreen(navController: NavController){
    val backgroundImage = painterResource(id = R.drawable.background3)
    // val underLogo = painterResource(id = R.drawable.logo3)


    Box(modifier = Modifier
        .fillMaxSize()
    ){

        Image(painter = backgroundImage,
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 39.dp, horizontal = 5.dp)
            .align(alignment = Alignment.Center)
            .alpha(0.94f),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(46.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = Color.White)
                        .padding(horizontal = 30.dp, vertical = 18.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        modifier = Modifier.padding(7.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Left,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = "Full Name",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "Mert Adalı")


                    Text(
                        text = "E-mail",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "deneme@gmail.com")


                    Text(
                        text = "Password",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialPasswordText(hint ="Password")

                    Spacer(modifier = Modifier.padding(2.dp))


                    PasswordCondition()

                    PrivacyPolicy(onClick = { /*TODO*/ })

                    SignUpButton(onClick = { navController.navigate("login_screen") })

                    OrSignUpWith()

                    GoogleSignUpButton(onClick = { /*TODO*/ })

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
        textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Start, fontSize = 14.sp),

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RectangleShape)
            .height(51.dp)
            .onFocusChanged {
                // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 13.sp)} // Placeholder kullanımı
    )

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
            .height(51.dp)
            .onFocusChanged {
                // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 13.sp)}// Placeholder kullanımı

    )
}

@Composable
fun PasswordCondition(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info, // Info ikonu kullanılıyor
            contentDescription = "Password Hint",
            tint = Color.Red, // İkon rengini ayarlayabilirsiniz
            modifier = Modifier.size(16.dp) // İkon boyutunu ayarlayın
        )
        Spacer(modifier = Modifier.width(4.dp)) // İkon ile metin arasında boşluk
        Text(
            text = "Password must be at least 6 characters.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun PrivacyPolicy(onClick: () -> Unit){

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

        Text(text = "I agree to the", color = Color.Gray, fontSize = 13.sp)
        Spacer(modifier = Modifier.padding(horizontal = 1.dp))
        Text(text = "privacy policy", color = MaterialTheme.colorScheme.secondary, fontSize = 13.sp,style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onClick() })


    }

}

@Composable
fun SignUpButton(onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 4.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(text ="Create Account", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold )

    }

}

@Composable
fun OrSignUpWith() {
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
            text = "or sign up with",
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
fun GoogleSignUpButton( onClick: () -> Unit){
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

        Text(text = "Do you already have account?",
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            fontSize = 14.sp)

        Text(text = "Login",
            color = Color.Blue,
            modifier = Modifier.clickable { onClick() },
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline))

    }

}



