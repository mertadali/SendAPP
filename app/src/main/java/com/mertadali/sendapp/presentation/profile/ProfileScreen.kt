package com.mertadali.sendapp.presentation.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mertadali.sendapp.R
import com.mertadali.sendapp.presentation.Screen
import com.mertadali.sendapp.presentation.feed.BottomNavBar
import com.mertadali.sendapp.presentation.login.SpecialTextField
import java.util.Calendar

@Composable
fun ProfileScreen(navController: NavController){

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

            Column(modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 12.dp)
                .fillMaxHeight()
                .align(alignment = Alignment.Center)) {

                val backgroundImage = painterResource(id = R.drawable.ic_launcher_background)

                Text(
                    text = "Profile",
                    modifier = Modifier.padding(7.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Left,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )


                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    Modifier
                        .size(140.dp, 120.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.padding(6.dp))

                    Text(
                        text = "Full name",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SpecialTextField(hint = "Mert Adalı")

                Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "E-mail",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SpecialTextField(hint = "mertadali605@gmail.com")

                Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "Job",
                        modifier = Modifier.padding(7.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SpecialTextField(hint = "Developer")

                Spacer(modifier = Modifier.padding(5.dp))

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
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
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
                        modifier = Modifier.size(24.dp)
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
            .padding(horizontal = 18.dp, vertical = 4.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text ="Save", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold )

    }

}

