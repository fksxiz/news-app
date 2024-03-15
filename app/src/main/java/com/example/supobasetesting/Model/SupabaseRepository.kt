package com.example.supobasetesting.Model

import com.example.supobasetesting.Common.News
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

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
}