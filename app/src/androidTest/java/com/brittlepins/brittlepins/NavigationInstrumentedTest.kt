package com.brittlepins.brittlepins

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brittlepins.brittlepins.authentication.LogInFragment
import com.brittlepins.brittlepins.authentication.LogInFragmentDirections
import com.brittlepins.brittlepins.authentication.SignUpFragment
import com.brittlepins.brittlepins.authentication.SignUpFragmentDirections
import com.brittlepins.brittlepins.start.StartFragment
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class NavigationInstrumentedTest {
    private lateinit var navController: NavController

    @Test
    fun mainActivityRendersStartFragment() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withParent(withId(R.id.main_container)))
            .check(matches(withChild(withId(R.id.start_container))))
    }

    @Test
    fun startFragmentNavigatesToAuthOnButtonClick() {
        navController = setUpNavController(R.navigation.start_navigation, StartFragment::class.java)

        onView(withId(R.id.start_to_log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.logInFragment))
    }

    @Test
    fun logInNavigatesToSignUpOnButtonClick() {
        navController = setUpNavController(R.navigation.auth_navigation, LogInFragment::class.java)

        onView(withId(R.id.log_in_to_sign_up_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.signUpFragment))
    }

    @Test
    fun signUpNavigatesToLogInOnButtonClick() {
        navController = setUpNavController(R.navigation.auth_navigation, SignUpFragment::class.java)
        navController.navigate(R.id.signUpFragment)

        onView(withId(R.id.sign_up_to_log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.logInFragment))
    }

    @Test
    fun navigationBetweenLogInAndSignUpPopsUpToTop() {
        navController = setUpNavController(R.navigation.auth_navigation, LogInFragment::class.java)

        repeat(15) {
            navController.navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }

        assertThat(navController.backStack.size, equalTo(2))
    }

    private inline fun <reified T : Fragment> setUpNavController(graphId: Int, fragment: Class<T>)
            : TestNavHostController {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setViewModelStore(ViewModelStore())
        navController.setGraph(graphId)

        val scenario = launchFragmentInContainer<T>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        return navController
    }
}
