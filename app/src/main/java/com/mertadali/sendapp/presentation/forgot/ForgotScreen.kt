package com.mertadali.sendapp.presentation.forgot

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ForgotScreen(
    navController: NavController,
    viewModel: ForgotViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    // Hata mesajı varsa toast gösterme
    LaunchedEffect(key1 = state.error) {
        state.error?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    // İşlem başarılı ise toast göster ve geri dön
    LaunchedEffect(key1 = state.isEmailSent) {
        if (state.isEmailSent) {
            Toast.makeText(context, "Şifre sıfırlama maili gönderildi. Gelen kutunuzu kontrol edin.", Toast.LENGTH_LONG).show()
            navController.popBackStack() // Giriş ekranına dönmek için.
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Şifreni Sıfırla",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(ForgotEvent.EnterEmail(it)) },
            label = { Text(text = "E-mail") },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.onEvent(ForgotEvent.SendResetMail) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = "Gönder")
            }
        }
    }
}