package com.mertadali.sendapp.presentation.plans.add_plan

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mertadali.sendapp.R
import com.mertadali.sendapp.presentation.Screen
import com.mertadali.sendapp.presentation.feed.BottomNavBar

@Composable
fun AddPlanScreen(navController: NavController){


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
                .padding(innerPadding)) {

            AddPlan()

        }

    }

}

@Composable
fun AddPlan() {
    var planName by remember { mutableStateOf("") }
    var planDetails by remember { mutableStateOf("") }
    val weatherInfo by remember { mutableStateOf("Sunny, 25°C    Karşıyaka/İzmir") } // Örnek hava durumu
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            )
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.baseline_sunny_24), // İcon ekleyin
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Weather: $weatherInfo",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }

        // Plan İsmi Girişi
        OutlinedTextField(
            value = planName,
            onValueChange = { planName = it },
            label = { Text("Plan Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Plan Detayları (Maddeler)
        OutlinedTextField(
            value = planDetails,
            onValueChange = { planDetails = it},
            label = { Text("Plan Details (Add tasks)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 7
        )

        // Resim Ekleme Kartı
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(7.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Image selection coming soon!", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Photo,
                    contentDescription = "Add Image(Optional)",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add Image (Optional)", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Harita Seçimi Kartı
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(7.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Map selection coming soon!", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Select Location(Optional)",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Select Location on Map (Optional)", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Button(
            onClick = {
                // Paylaş işlemi
                if (planName.isEmpty() || planDetails.isEmpty()) {
                    Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Plan shared successfully!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 26.dp, horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021727))
        ) {
            Text(text = "Share Plan", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }


    }
}



















