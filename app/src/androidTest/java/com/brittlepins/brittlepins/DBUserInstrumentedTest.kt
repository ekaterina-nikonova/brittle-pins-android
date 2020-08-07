package com.brittlepins.brittlepins

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.brittlepins.brittlepins.db.AppDatabase
import com.brittlepins.brittlepins.db.User
import com.brittlepins.brittlepins.db.UserDAO
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DBUserInstrumentedTest {
    private lateinit var db: AppDatabase
    private lateinit var userDAO: UserDAO
    private lateinit var user: User

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        createDB()
        user = DBTestUtils.createUser()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        closeDB()
    }

    @Test
    fun getInstanceReturnsDB() {
        assertThat(AppDatabase.getInstance(context), instanceOf(RoomDatabase::class.java))
    }

    @Test
    @Throws(Exception::class)
    fun readsAndWritesUser() {
        userDAO.insert(user)
        val userFromDB = userDAO.getUser(user.id)
        assertThat(userFromDB.id, equalTo(user.id))
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
