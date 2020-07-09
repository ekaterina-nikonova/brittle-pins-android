package com.brittlepins.brittlepins

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brittlepins.brittlepins.start.StartFragment
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationInstrumentedTest {

    @Test
    fun mainActivityRendersStartFragment() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withParent(withId(R.id.main_container))).check(matches(withChild(withId(R.id.start_container))))
    }

    @Test
    fun startFragmentNavigatesToAuthOnButtonClick() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.start_navigation)

        val scenario = launchFragmentInContainer<StartFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.start_to_log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.logInFragment))
    }
}
