package com.example.mytodolist.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytodolist.AuthState
import com.example.mytodolist.AuthViewModel


//Login Screen for User SignIn
@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                navController: NavController,
                authViewModel: AuthViewModel) {

    var email by remember{                                      // Remember Function Used to Retain the State of Email and Password Across UI Recompositions
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var isPasswordVisible by remember {                        // To Hide Our Typed Password
        mutableStateOf(false) }


    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Approved -> navController
                .navigate("Home")                        // Navigation to HomeScreen After Successful Login
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error)
                    .message, Toast.LENGTH_LONG
            ).show()

            else -> Unit                                       // Returns Nothing
        }
    }


    Column(                                                    // Column Used to Add All the Items in Vertical Order
        modifier = Modifier                                    // Modifiers Used to Style our UI Appearance
            .fillMaxSize(),                                    // Used to Fill Contents in the Column Full Width
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "My Todo App", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Please Login", fontSize = 20.sp)          // Text Composable to Display Text in UI

        Spacer(modifier = Modifier                             // Spacer Used for Layout Spacing to Separate Components
            .height(15.dp))

        OutlinedTextField(                                     // To be used for user Input to Enter Email
            value = email,
            onValueChange = {email=it},                        // To Store Typed Email in our email Variable
            label = { Text(text = "Enter Email Address")
            })

        Spacer(modifier = Modifier
            .height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password=it},                      // To Store Typed Password in the password Variable
            label = { Text(text = "Enter Password")
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation     // To Hide Password
                .None else PasswordVisualTransformation(),
            )

        Spacer(modifier = Modifier
            .height(20.dp))

        Button(onClick = {                                       // Clickable button to execute login process
            authViewModel.Login(email, password)
        })
        {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier
            .height(15.dp))

        TextButton(onClick = {
            navController.navigate("SignUp")              // To Navigate to SignUp Page
        }) {
            Text(text = "Don't have an account? Sign Up")
        }

    }


}