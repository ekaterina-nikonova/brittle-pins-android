package com.brittlepins.brittlepins

import com.brittlepins.brittlepins.authentication.login.LogIn
import com.brittlepins.brittlepins.network.AuthServices
import com.brittlepins.brittlepins.network.UserClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthenticationUnitTest {
    private lateinit var testEmail : String
    private lateinit var testPassword : String

    @Before
    fun setUp() {
        testEmail = "test@example.com"
        testPassword = "testPassword1234"
    }

    @Test
    fun logInHasEmailAndPassword() {
        val login = LogIn(email = testEmail, password = testPassword)
        assertEquals(login.email, testEmail)
        assertEquals(login.password, testPassword)
    }
}
