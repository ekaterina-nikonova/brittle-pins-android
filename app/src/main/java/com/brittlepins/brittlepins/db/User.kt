package com.brittlepins.brittlepins.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

enum class USER_ROLES {
    guest, user, manager, admin
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val email: String,
    val username: String,
    val role: String
)
