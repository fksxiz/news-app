package com.example.supobasetesting.Common

import android.util.Patterns
import java.util.regex.Pattern

object Validator {
    fun validateEmail(email:String):Boolean{
        return Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-z0-9][a-z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-z0-9][a-z0-9\\-]{0,25}" +
                    ")+"
        ).matcher(email).matches()
    }
}