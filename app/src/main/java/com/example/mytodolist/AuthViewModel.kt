package com.example.mytodolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()  // To Check Authentication Status

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState              // Exposing the Authentication variable in the UI

    init {                                                       // init block to initialize Authentication Checks
        checkAuthentication()
    }

    fun checkAuthentication() {                                  // To Check if User is Authenticated or Not

        if (auth.currentUser == null) {
            _authState.value = AuthState.Denied                  // User Credentials Not Authenticated Status
        } else {
            _authState.value = AuthState.Approved                // User Credentials Authenticated Status
        }
    }

    fun Login(email : String, password : String){                // Login Function

        if (email.isEmpty() || password.isEmpty()){              // To Bypass Login Process if User Details are Missing
            _authState.value = AuthState.Error(
                "Please fill out the email and password fields")
            return
        }

        _authState.value = AuthState.Connecting
        auth.signInWithEmailAndPassword(email,password)          // Firebase Authentication Method for SignIn
            .addOnCompleteListener{task->
                if (task.isSuccessful){                          // User Successfully LoggedIn
                    _authState.value = AuthState.Approved
                }
                else{
                    _authState.value = AuthState.Error(          // User Login Unsuccessful
                        task.exception?.message?:
                        "Something is not right")
                }
            }
    }

    fun SignUp(email : String, password : String){

        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error(
                "Please fill out the email and password fields")
            return
        }

        _authState.value = AuthState.Connecting
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    _authState.value = AuthState.Approved
                }
                else{
                    _authState.value = AuthState.Error(
                        task.exception?.message?:
                        "Something is not right")
                }
            }
    }
    fun LogOut(){                                                      // Firebase SignOut Method
        auth.signOut()
        _authState.value = AuthState.Denied
    }


}

sealed class AuthState{                                                // Used to Manage Authentication State

    object Approved : AuthState()
    object Denied : AuthState()
    object Connecting : AuthState()
    data class Error(val message : String) : AuthState()

}