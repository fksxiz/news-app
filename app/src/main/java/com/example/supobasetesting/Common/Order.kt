package com.example.supobasetesting.Common

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    var id:Int=-1,
    var title:String="",
    var content:String=""
)
