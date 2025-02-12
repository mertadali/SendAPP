@file:Suppress("DEPRECATION")

package com.mertadali.sendapp.presentation.sign_up

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mertadali.sendapp.R
import com.mertadali.sendapp.presentation.Screen

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()){
    val backgroundImage = painterResource(id = R.drawable.background3)






    val state = viewModel.state.collectAsState().value
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
                    viewModel.handleGoogleSignUp(token)
                }
            } catch (e: ApiException) {
                Toast.makeText(context, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hata mesajı için LaunchedEffect
    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

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

                    // Hata mesajı gösterimi
                    if (state.errorMessage != null) {
                        Text(
                            text = state.errorMessage,
                            color = Color.Red,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 7.dp, vertical = 4.dp),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Left
                        )
                    }


                    Text(
                        text = "Full Name",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "Mert Adalı", onValueChange = { viewModel.onEvent(SignUpEvent.EnterFullName(it)) })



                    Text(
                        text = "E-mail",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialTextField(hint = "deneme@gmail.com", onValueChange = { viewModel.onEvent(SignUpEvent.EnterEmail(it)) })


                    Text(
                        text = "Password",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SpecialPasswordText(hint ="Password", onValueChange = { viewModel.onEvent(SignUpEvent.EnterPassword(it)) })

                    Spacer(modifier = Modifier.padding(2.dp))


                    PasswordCondition()

                    PrivacyPolicy()


                    // Loading gösterimi
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp)
                        )
                    }



                    SignUpButton(viewModel, state)

                    OrSignUpWith()

                    GoogleSignUpButton(onClick = {
                        try {
                            launcher.launch(googleSignInClient.signInIntent)

                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Error launching Google Sign In: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

                    AskAccount(onClick = {navController.navigate(Screen.LoginScreen.route)})

                    //Başarılı kayıt durumunda yönlendirme
                    LaunchedEffect(key1 = state.isSignUpSuccessful) {
                        if (state.isSignUpSuccessful) {
                            Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.FeedScreen.route) {
                                popUpTo(Screen.SignUpScreen.route) { inclusive = true }
                            }
                        }
                    }



                }
            }
        }
    }
}

@Composable
fun SpecialTextField(hint: String, onValueChange: (String) -> Unit){

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
            onValueChange(it) },
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
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
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
fun PrivacyPolicy(){
    var checked by remember { mutableStateOf(false) }
    var showPrivacyPolicy by remember { mutableStateOf(false) }
    val scaleMultiplier = 0.9f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            modifier = Modifier.scale(scaleMultiplier),
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.LightGray,
                uncheckedColor = Color.LightGray,
                checkmarkColor = Color.White
            )
        )

        Text(text = "I agree to the", color = Color.Gray, fontSize = 13.sp)
        Spacer(modifier = Modifier.padding(horizontal = 1.dp))
        Text(
            text = "privacy policy",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 13.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { showPrivacyPolicy = true }
        )
    }

    if (showPrivacyPolicy) {
        Dialog(onDismissRequest = { showPrivacyPolicy = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Privacy Policy",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = """
                                Privacy Policy for SendApp
                                
                                Last updated: March 2024
                                
                                1. Introduction
                                Welcome to SendApp. We respect your privacy and are committed to protecting your personal data.
                                
                                2. Information We Collect
                                - Email address
                                - Name and surname
                                - Profile information
                                - Location data (with your permission)
                                
                                3. How We Use Your Information
                                - To provide and maintain our services
                                - To notify you about changes
                                - To provide customer support
                                - To detect and prevent fraud
                                
                                4. Data Security
                                We implement appropriate security measures to protect your data.
                                
                                5. Your Rights
                                You have the right to:
                                - Access your data
                                - Correct your data
                                - Delete your data
                                - Object to processing
                                
                                6. Contact Us
                                Email: support@sendapp.com
                                
                                7. Changes
                                We may update this policy from time to time.
                                
                                8. Consent
                                By using our app, you consent to this privacy policy.
                            """.trimIndent(),
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }

                    Button(
                        onClick = { showPrivacyPolicy = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpButton(viewModel: SignUpViewModel, state: SignUpState){
    Button(
        onClick = {
            viewModel.onEvent(SignUpEvent.SignUpClicked) },
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 4.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(2.dp)
    ) {
        if (!state.isLoading) {
            Text(
                text = "Create Account",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

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



