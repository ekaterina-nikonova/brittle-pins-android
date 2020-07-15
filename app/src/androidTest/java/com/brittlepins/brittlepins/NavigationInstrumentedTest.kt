package com.brittlepins.brittlepins

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brittlepins.brittlepins.authentication.login.LogInFragment
import com.brittlepins.brittlepins.authentication.login.LogInFragmentDirections
import com.brittlepins.brittlepins.authentication.SignUpFragment
import com.brittlepins.brittlepins.authentication.SignUpFragmentDirections
import com.brittlepins.brittlepins.main.MainNavActivity
import com.brittlepins.brittlepins.start.StartFragment
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

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
        navController = setUpNavController<StartFragment>(R.navigation.start_navigation)

        onView(withId(R.id.start_to_log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.logInFragment))
    }

    @Test
    fun logInNavigatesToSignUpOnButtonClick() {
        navController = setUpNavController<LogInFragment>(R.navigation.auth_navigation)

        onView(withId(R.id.log_in_to_sign_up_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.signUpFragment))
    }

    @Test
    fun signUpNavigatesToLogInOnButtonClick() {
        navController = setUpNavController<SignUpFragment>(R.navigation.auth_navigation)
        navController.navigate(R.id.signUpFragment)

        onView(withId(R.id.sign_up_to_log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.logInFragment))
    }

    @Test
    fun navigationBetweenLogInAndSignUpPopsUpToTop() {
        navController = setUpNavController<LogInFragment>(R.navigation.auth_navigation)

        repeat(15) {
            navController.navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }

        assertThat(navController.backStack.size, equalTo(2))
    }

    @Test
    fun navigateFromLogInToMain() {
        navController = setUpNavController<LogInFragment>(R.navigation.auth_navigation)
        onView(withId(R.id.log_in_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.mainNavActivity))
    }

    @Test
    fun navigateFromSignUpToMain() {
        navController = setUpNavController<SignUpFragment>(R.navigation.auth_navigation)
        navController.navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())

        onView(withId(R.id.sign_up_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, equalTo(R.id.mainNavActivity))
    }

    @Test
    fun mainNavRendersProjectsOnLaunch() {
        navController = setUpNavControllerWithActivity<MainNavActivity>(
            R.navigation.main_navigation, R.id.main_nav_container)

        assertThat(navController.currentDestination?.id, equalTo(R.id.navigation_projects))
    }

    @Test
    fun mainNavNavigatesToBoards() {
        ActivityScenario.launch(MainNavActivity::class.java)

        onView(withId(R.id.navigation_boards)).perform(ViewActions.click())
        onView(withId(R.id.nav_host_fragment_main))
            .check(matches(withChild(withId(R.id.boards_container))))
    }

    @Test
    fun mainNavNavigatesToComponents() {
        ActivityScenario.launch(MainNavActivity::class.java)

        onView(withId(R.id.navigation_components)).perform(ViewActions.click())
        onView(withId(R.id.nav_host_fragment_main))
            .check(matches(withChild(withId(R.id.components_container))))
    }

    @Test
    fun mainNavNavigatesToPublic() {
        ActivityScenario.launch(MainNavActivity::class.java)

        onView(withId(R.id.navigation_public)).perform(ViewActions.click())
        onView(withId(R.id.nav_host_fragment_main))
            .check(matches(withChild(withId(R.id.public_container))))
    }

    @Test
    fun mainNavNavigatesToSettings() {
        ActivityScenario.launch(MainNavActivity::class.java)

        onView(withId(R.id.navigation_settings)).perform(ViewActions.click())
        onView(withId(R.id.nav_host_fragment_main))
            .check(matches(withChild(withId(R.id.settings_container))))
    }

    private inline fun <reified T : Fragment> setUpNavController(graphId: Int)
            : TestNavHostController {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(graphId)

        val scenario = launchFragmentInContainer<T>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        return navController
    }

    private inline fun <reified T : Activity> setUpNavControllerWithActivity(graphId: Int, viewId: Int)
            : TestNavHostController {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(graphId)

        val scenario = ActivityScenario.launch(T::class.java)
        scenario.onActivity {
            Navigation.setViewNavController(it.findViewById(viewId), navController)
        }
        return navController
    }
}
