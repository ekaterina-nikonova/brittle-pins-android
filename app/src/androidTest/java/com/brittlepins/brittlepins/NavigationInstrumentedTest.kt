package com.brittlepins.brittlepins

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brittlepins.brittlepins.start.StartFragment
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationInstrumentedTest {

    @Test
    fun startNavigationOnLaunch() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.start_navigation)

        val titleScenario = launchFragmentInContainer<StartFragment>()
        titleScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        assertThat(navController.currentDestination?.id, equalTo(R.id.startFragment))
    }
}
