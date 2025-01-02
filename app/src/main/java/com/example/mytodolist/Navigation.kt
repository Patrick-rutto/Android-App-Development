package com.example.mytodolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodolist.screens.HomeScreen
import com.example.mytodolist.screens.LoginScreen
import com.example.mytodolist.screens.SignUpScreen


@Composable
fun Navigation (modifier: Modifier = Modifier, authViewModel: AuthViewModel){
    val navController = rememberNavController()                                     // NavHost Controller For Navigation Between Screens

    NavHost(navController = navController, startDestination = "Login", builder = {  // NavHost For Hosting All the Screens in Our Application
        composable("Login"){                                                  // Route to LoginScreen
            LoginScreen(modifier,navController,authViewModel)
        }
        composable("SignUp"){                                                 // Route to SignUpScreen
            SignUpScreen(modifier,navController,authViewModel)
        }
        composable("Home"){                                                   // Route to HomeScreen
            HomeScreen(modifier,navController,authViewModel)
        }
    })
}