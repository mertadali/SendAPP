package com.mertadali.sendapp.presentation.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertadali.sendapp.presentation.Screen

@Composable
fun FeedScreen(navController: NavController) {



    Scaffold( modifier = Modifier.padding(bottom = 6.dp),
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
            Column {
                SearchBar(hint = "Search Name", onSearch = {
                    // viewModel.onEvent
                })


                // RecyclerView
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    // Movie ListRow yapılacak.


                }


            }
        }

    }
}


@Composable
fun SearchBar(hint: String, onSearch: (String) -> Unit = {}) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint.isNotEmpty()) }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            keyboardActions = KeyboardActions(onDone = { onSearch(text) }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(color = Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged { focusState ->
                    isHintDisplayed = !focusState.isFocused && text.isEmpty()
                }
        )
    }
}


@Composable
fun BottomNavBar(navController: NavController, items: List<Screen>) {

    val currentRoute = navController.currentDestination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = CircleShape)
            .alpha(0.93f)
            .blur(0.1.dp)){
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            NavigationBarItem(

                selected = isSelected,
                icon = {

                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(
                            if (isSelected) Color.White else Color.Gray,
                            shape = CircleShape
                        ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = screen.icon ?: Icons.Default.Home,
                            contentDescription = screen.route,
                            modifier = Modifier
                                .padding(8.dp),
                            tint = if (isSelected) Color.Black else Color.White

                        )
                    }
                },
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}













