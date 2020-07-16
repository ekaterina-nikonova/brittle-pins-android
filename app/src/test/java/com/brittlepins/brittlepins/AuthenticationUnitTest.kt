package com.brittlepins.brittlepins

import com.brittlepins.brittlepins.authentication.login.LogIn
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
