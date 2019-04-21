package com.example.samplechatapplication

import java.io.Serializable

data class User(
    var nickname: String? = null,
    var profileUrl: Int = 0,
    var id: Int? = null
):Serializable