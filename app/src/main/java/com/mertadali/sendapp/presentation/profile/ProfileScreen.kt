package com.mertadali.sendapp.presentation.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mertadali.sendapp.R
import com.mertadali.sendapp.presentation.Screen
import com.mertadali.sendapp.presentation.feed.BottomNavBar
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Profile",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    ),
                    actions = {
                        IconButton(onClick = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "Logout",
                                tint = Color.Black,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    },
                    modifier = Modifier.height(64.dp)
                )
                HorizontalDivider(
                    color = Color(0xFFE0E0E0),
                    thickness = 1.dp
                )
            }
        },
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
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)) {

                val backgroundImage = painterResource(id = R.drawable.ic_launcher_background)

                Box(modifier = Modifier.padding(vertical = 11.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                    Image(
                        painter = backgroundImage,
                        contentDescription = null,
                        Modifier
                            .size(130.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Full name",
                    color = Color.Black,
                    modifier = Modifier.padding(7.dp),
                    textAlign = TextAlign.Left,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                SpecialTextField(hint = "Mert Adalı", modifier = Modifier.padding(0.1.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "E-mail",
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(7.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                SpecialTextField(hint = "mertadali605@gmail.com", modifier = Modifier.padding(0.1.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Job",
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(7.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                SpecialTextField(hint = "Developer",
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp))

                Spacer(modifier = Modifier.height(8.dp))

                BirthDate()

                SaveButton()



            }
        }
    }
}

@Composable
fun BirthDate() {
    var birthDate by remember { mutableStateOf("Select Birth Date") }
    var isDatePickerVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val datePickerState = remember { mutableStateOf<Calendar?>(null) }
    val dateFormatter = java.text.SimpleDateFormat("dd MMMM yyyy", context.resources.configuration.locales[0])

    Column(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Text(
            text = "Birth Date",
            modifier = Modifier.padding(bottom = 6.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { isDatePickerVisible = true },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F0F0),
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24), // İcon ekleyin
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = if (birthDate == "Select Birth Date") "Select Birth Date" else birthDate,
                        color = if (birthDate == "Select Birth Date") Color.Gray else Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }

        // Modern DatePickerDialog
        if (isDatePickerVisible) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    birthDate = dateFormatter.format(calendar.time) // Güzelleştirilmiş tarih formatı
                    isDatePickerVisible = false
                },
                year,
                month,
                day
            ).apply {
                setOnCancelListener {
                    isDatePickerVisible = false
                }
            }.show()
        }
    }
}


@Composable
fun SaveButton(){   // onClick: () -> Unit
    Button(
        onClick = { },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text ="Save", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold )

    }

}

@Composable
fun SpecialTextField(hint: String,modifier: Modifier){

    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    TextField(
        value = text,
        onValueChange = { text = it},
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


