package com.example.mytodolist.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytodolist.AuthState
import com.example.mytodolist.AuthViewModel
import com.example.mytodolist.MyToDoListPage
import com.example.mytodolist.ToDoPlanViewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier,                        //HomeScreen To Display Features of Our UI
               navController: NavController,
               authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Denied -> navController                     // Navigation to Login Page Following SignOut
                .navigate("Login")
            else -> Unit
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()                                           // Makes the Box occupy the entire screen
    ) {
        TextButton(
            onClick = { authViewModel.LogOut() },
            modifier = Modifier
                .align(Alignment.TopEnd)                             // Align the text button to the top-right
                .padding(18.dp)                                      // Add padding to avoid sticking to the edges
        ) {
            Text("Sign Out")
        }
    }

    MyToDoListPage(ToDoPlanViewModel())
}




