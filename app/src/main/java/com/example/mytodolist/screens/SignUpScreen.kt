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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytodolist.AuthState
import com.example.mytodolist.AuthViewModel



@Composable
fun SignUpScreen(modifier: Modifier = Modifier,                   // SignUp Screen for User Registration
                 navController: NavController,
                 authViewModel: AuthViewModel){

    var email by remember{                                        // To Retain the State of Email and Password Across UI Recompositions
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }


    val authState = authViewModel.authState                       // Observes AuthState Change and Reflect it in the UI
        .observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {                            // Navigation to HomeScreen After Successful Authentication
        when (authState.value) {
            is AuthState.Approved -> navController
                .navigate("Home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error)
                    .message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit                                          // Returns Nothing
        }
    }

    Column(                                                      // Column Used to Add All the Items in Vertical Order
        modifier = Modifier
            .fillMaxSize(),                                      // Modifiers Used to Style our UI Appearance
        verticalArrangement = Arrangement.Center,                // Used to Fill Contents in the Column Full Width
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Please SignUp", fontSize = 30.sp)           // Text Composable to Display Text in UI

        Spacer(modifier = Modifier                               // Spacer Used for Layout Spacing to Separate Components
            .height(15.dp))

        OutlinedTextField(                                        // To be used for user Input to Enter Email
            value = email,
            onValueChange = {email=it},                           // To Store Typed Email in the email Variable
            label = { Text(text = "Enter Your Email Address")
            })

        Spacer(modifier = Modifier
            .height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password=it},                          // To Store Typed Password in the password Variable
            label = { Text(text = "Enter Your Password")
            })

        Spacer(modifier = Modifier
            .height(20.dp))

        Button(onClick = {
            authViewModel.SignUp(email,password)                    // Clickable Button to Execute SignUp Process
        })
        {
            Text(text = "Create Account")
        }

        Spacer(modifier = Modifier
            .height(15.dp))

        TextButton(onClick = {
            navController.navigate("Login")                   // To Navigate Back to the Login Screen
        }) {
            Text(text = "Already have an account? Login")
        }

    }

}
