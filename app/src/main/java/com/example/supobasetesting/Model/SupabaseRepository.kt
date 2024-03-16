package com.example.supobasetesting.Model

import android.util.Log
import com.example.supobasetesting.Common.News
import com.example.supobasetesting.Common.Token
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import java.lang.Exception

class SupabaseRepository {
    private val supabase = createSupabaseClient(
        supabaseUrl = "https://mmviqimgexkjdvmlasua.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1tdmlxaW1nZXhramR2bWxhc3VhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTAyNjM3OTEsImV4cCI6MjAyNTgzOTc5MX0.UTttVYVHIdYfN9J1ta-WJ3gl8uCz-rYGwdLKxl20vY8"
    ) {
        install(Auth)
        install(Postgrest)
    }

    suspend fun getNews(callback:(List<News>)->Unit){
        callback(supabase.postgrest["News"].select().decodeList<News>())
    }

    suspend fun createUser(userEmail:String, userPassword:String,callback: (Boolean) -> Unit){
        try {
            val user = supabase.auth.signUpWith(Email) {
                email = userEmail
                password = userPassword
            }
            Token.token = supabase.auth.currentAccessTokenOrNull()
            com.example.supobasetesting.Common.Email.email=userEmail
            callback(true)
        }catch (e:Exception){
            Log.e("create user error",e.message.toString())
            callback(false)
        }
    }

    suspend fun signInUser(userEmail:String, userPassword:String,callback: (Boolean) -> Unit){
        try {
            val user = supabase.auth.signInWith(Email) {
                email = userEmail
                password = userPassword
            }
            Token.token = supabase.auth.currentAccessTokenOrNull()
            com.example.supobasetesting.Common.Email.email=userEmail
            callback(true)
        }catch (e:Exception){
            Log.e("login user error",e.message.toString())
            callback(false)
        }
    }

    suspend fun loginThroughOTP(userEmail: String,userToken:String,callback: (Boolean) -> Unit){
        try {
            supabase.auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = userEmail,
                token = userToken
            )
            callback(true)
        }catch (e:Exception){
            Log.e("OTP Exception",e.message.toString())
            callback(false)
        }
    }

    suspend fun resendOTP(userEmail: String,callback: (Boolean) -> Unit){
        try{
            supabase.auth.resendEmail(OtpType.Email.EMAIL, userEmail)
            callback(true)
        }catch (e:Exception){
            Log.e("OTP Resend Exception",e.message.toString())
            callback(false)
        }
    }
}