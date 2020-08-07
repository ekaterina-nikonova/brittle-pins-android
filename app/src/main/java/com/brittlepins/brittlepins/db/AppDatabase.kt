package com.brittlepins.brittlepins.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}
