package com.brittlepins.brittlepins

import android.content.res.Resources
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.brittlepins.brittlepins.authentication.login.LogInFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInInstrumentedTest {
    private val resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources

    @Test
    fun logInScreenHasUsernameOrEmailEditText() {
        launchFragmentInContainer<LogInFragment>()
        onView(withId(R.id.log_in_form))
            .check(matches(withChild(withHint(resources.getString(R.string.log_in_username_hint)))))
    }
}
