package com.example.supobasetesting.Common

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id:Int=0,
    val title:String="",
    val text:String="",
    val author:String="unknown"
)
