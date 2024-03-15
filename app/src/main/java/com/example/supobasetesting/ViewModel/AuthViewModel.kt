package com.example.supobasetesting.ViewModel

import androidx.lifecycle.ViewModel
import com.example.supobasetesting.Model.SupabaseRepository

class AuthViewModel:ViewModel() {
    private val client = SupabaseRepository()

    suspend fun createUser(userEmail:String, userPassword:String,callback: (Boolean) -> Unit){
        client.createUser(userEmail,userPassword){
            callback(it)
        }
    }

    suspend fun loginUser(userEmail:String, userPassword:String,callback: (Boolean) -> Unit){
        client.signInUser(userEmail,userPassword){
            callback(it)
        }
    }

    suspend fun loginOTP(userEmail: String,userToken:String,callback: (Boolean) -> Unit){
        client.loginThroughOTP(userEmail,userToken){
            callback(it)
        }
    }

    suspend fun resendOTP(userEmail: String,callback: (Boolean) -> Unit){
        client.resendOTP(userEmail){
            callback(it)
        }
    }
}