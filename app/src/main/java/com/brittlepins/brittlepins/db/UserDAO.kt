package com.brittlepins.brittlepins.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Query("SELECT * from users WHERE id = :id")
    fun getUser(id: String) : User
}
