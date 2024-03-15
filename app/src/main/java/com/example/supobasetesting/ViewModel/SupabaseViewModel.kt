package com.example.supobasetesting.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.supobasetesting.Common.News
import com.example.supobasetesting.Model.SupabaseRepository

class SupabaseViewModel:ViewModel() {
    var news = MutableLiveData<List<News>>()
    val repository = SupabaseRepository()

    suspend fun getNews(callback:(List<News>)->Unit){
        repository.getNews(){
            news.postValue(it)
        }
    }
}