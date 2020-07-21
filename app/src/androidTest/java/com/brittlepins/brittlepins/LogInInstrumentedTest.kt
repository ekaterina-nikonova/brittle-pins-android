package com.brittlepins.brittlepins

import android.content.res.Resources
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.brittlepins.brittlepins.authentication.login.LogIn
import com.brittlepins.brittlepins.authentication.login.LogInFragment
import com.brittlepins.brittlepins.network.AuthServices
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInInstrumentedTest {
    private val resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources

    private lateinit var logIn: LogIn

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        logIn = LogIn("test@example.com", "testPassword1234")
    }

    @Test
    fun logInScreenHasUsernameOrEmailEditText() {
        launchFragmentInContainer<LogInFragment>()
        onView(withId(R.id.log_in_form))
            .check(matches(withChild(withHint(resources.getString(R.string.log_in_username_hint)))))
    }

    @Test
    fun logInScreenHasPasswordEditText() {
        launchFragmentInContainer<LogInFragment>()
        onView(withId(R.id.log_in_form))
            .check(matches(withChild(withHint(resources.getString(R.string.log_in_password_hint)))))
    }

    @Test
    fun logInScreenCapturesEmail() {
        mockkObject(AuthServices)
        val slot = slot<LogIn>()

        every {
            AuthServices.logIn(capture(slot))
        } just Runs

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.auth_navigation)

        val scenario = launchFragmentInContainer<LogInFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.log_in_username_edit_text))
            .perform(ViewActions.replaceText(logIn.email))
        onView(withId(R.id.log_in_button))
            .perform(ViewActions.click())

        verify { AuthServices.logIn(slot.captured) }
    }
}