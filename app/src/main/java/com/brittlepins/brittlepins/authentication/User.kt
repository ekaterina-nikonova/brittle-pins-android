package com.brittlepins.brittlepins.authentication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val email: String,
    val username: String,
    val token: String,
    val csrf: String
)
