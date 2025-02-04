@file:Suppress("DEPRECATION")

package com.mertadali.sendapp.presentation.login

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mertadali.sendapp.R
import com.mertadali.sendapp.presentation.Screen
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation
import com.mertadali.sendapp.presentation.forgot.ForgotScreen

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("181469459994-qrb0eq30b2bk2lph6ud6llv0ccnfoe1e.apps.googleusercontent.com")  // Replace with your actual web client ID
            .requestEmail()
            .build())
    }

    DisposableEffect(Unit) {
        onDispose {
            googleSignInClient.signOut()
        }
    }





    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let { token ->
                    viewModel.handleGoogleSignIn(token)
                }
            } catch (e: ApiException) {
                Toast.makeText(context, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val backgroundImage = painterResource(id = R.drawable.background3)


    Box(modifier = Modifier
        .fillMaxSize()
    ){

        Image(painter = backgroundImage,
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 29.dp, horizontal = 5.dp)
            .align(alignment = Alignment.Center)
            .alpha(0.94f),
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
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive
            )


            Spacer(modifier = Modifier.padding(vertical = 5.dp))

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
                        .padding(horizontal = 30.dp, vertical = 12.dp)
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
                    SpecialTextField(
                        hint = "Enter your Email",
                        modifier = Modifier.padding(0.1.dp),
                        onValueChange = { viewModel.onEvent(LoginEvent.EnterEmail(it)) }
                    )


                    Text(
                        text = "Password",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold


                    )
                    SpecialPasswordText(
                        hint = "Enter Your Password",
                        onValueChange = { viewModel.onEvent(LoginEvent.EnterPassword(it)) })

                    Spacer(modifier = Modifier.padding(2.dp))


                    SpecialForgotPassword(onClick = { navController.navigate(Screen.ForgotScreen.route)})


                    CheckBoxLoggedIn(onClick = {
                        viewModel.onEvent(LoginEvent.IsLoggedIn(it))
                    })



                    LoginButton(onClick = { viewModel.onEvent(LoginEvent.ClickLogin) })




                    OrLoginWith()

                    GoogleSignInButton(

                        onClick = {

                            try {
                                /*     googleSignInClient.signOut().addOnCompleteListener {
                                         val signInIntent = googleSignInClient.signInIntent
                                         launcher.launch(signInIntent)
                                         googleSignInClient.signInIntent.extras?.clear()
                                                                        }
                                 */
                                launcher.launch(googleSignInClient.signInIntent)

                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Error launching Google Sign In: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )


                    AskAccount(onClick = {navController.navigate(Screen.SignUpScreen.route)})


                    // Loading Indicator
                    if (state.isLoadingState) {
                        CircularProgressIndicator(modifier = Modifier.padding(top = 10.dp))
                    }
                    /*   LaunchedEffect(key1 = state.isLoggedIn) {
                           if (state.isLoggedIn) {
                               navController.navigate(Screen.FeedScreen.route) {
                                   popUpTo(Screen.LoginScreen.route) {
                                       inclusive = true
                                   }
                               }
                           }
                       }

                     */
                    val context = LocalContext.current
                    LaunchedEffect(key1 = state.errorMessageState) {
                        if (state.errorMessageState != null) {
                            Toast.makeText(context, state.errorMessageState, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpecialTextField(hint: String, modifier: Modifier, onValueChange: (String) -> Unit){

    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
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
fun SpecialPasswordText(hint: String, onValueChange: (String) -> Unit){
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Start, fontSize = 14.sp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = Color.Gray
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RectangleShape)
            .height(51.dp)
            .onFocusChanged {
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            },
        placeholder = { Text(hint, color = Color.Gray, fontSize = 13.sp)}
    )
}

@Composable
fun CheckBoxLoggedIn(onClick: (Boolean) -> Unit){

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
            onCheckedChange = { onClick(it)},
            colors = CheckboxDefaults.colors(checkedColor = Color.LightGray, uncheckedColor = Color.LightGray, checkmarkColor = Color.White))

        Text(text = "Keep me logged in.", color = Color.Gray, fontSize = 13.sp)

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
        Text(text ="Login", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold )

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
                .height(55.dp)
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
fun AskAccount(onClick: () -> Unit,){
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
            modifier = Modifier.clickable { onClick()},
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline))

    }

}



