package com.brittlepins.brittlepins

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brittlepins.brittlepins.db.AppDatabase
import com.brittlepins.brittlepins.db.UserDAO
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBUserInstrumentedTest {
    private lateinit var db: AppDatabase
    private lateinit var userDAO: UserDAO

    @Before
    fun setup() {
        createDB()
    }

    @After
    fun teardown() {
        closeDB()
    }

    private fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDAO = db.userDAO
    }

    private fun closeDB() {
        db.close()
    }
}
