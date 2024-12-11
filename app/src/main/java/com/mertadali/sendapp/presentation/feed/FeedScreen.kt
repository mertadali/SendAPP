package com.mertadali.sendapp.presentation.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertadali.sendapp.presentation.Screen

@Composable
fun FeedScreen(navController: NavController) {


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
            Column {
                SearchBar(modifier = Modifier.fillMaxWidth(), hint = "Search Name", onSearch = {
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
fun SearchBar(modifier: Modifier, hint : String, onSearch : (String) -> Unit = {} ){
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(value = text,
            onValueChange = { text = it },
            keyboardActions = KeyboardActions(onDone = { onSearch(text) }),

            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(color = Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    // kullanıcı tıkladıysa hint gözükmesin istiyoruz
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                })


        if (isHintDisplayed) {
            Text(text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))

            println(isHintDisplayed)
        }
    }
}


@Composable
fun BottomNavBar(navController: NavController,items : List<Screen>){
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == screen.route,
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = screen.route) } },
                label = { Text(text = screen.route.replace("_screen","").replaceFirstChar { it.uppercase() })},
                onClick = {
                    if (navController.currentDestination?.route != screen.route){
                        navController.navigate(screen.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }


}










