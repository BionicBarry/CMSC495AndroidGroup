package com.example.samplechatapplication

import java.io.Serializable

data class Message(
    var message: String?,
    var sender: User?,
    var createdAt: String?
) : Serializable