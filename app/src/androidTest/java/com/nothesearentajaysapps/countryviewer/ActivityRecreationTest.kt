package com.nothesearentajaysapps.countryviewer

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.Thread.sleep

/**
 * Instrumented tests, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ActivityRecreationTest {

    companion object {
        const val WAIT_TIME_BETWEEN_TEST_PHASES = 10000L
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nothesearentajaysapps.countryviewer", appContext.packageName)
    }

    /**
     * Simulates a low resource situation where the Android system would destroy our Activity.
     * This test exercises how we recreate said Activity.
     *
     * Based on:
     * - https://developer.android.com/guide/components/activities/testing
     * - https://medium.com/google-developer-experts/stepping-into-activity-tests-with-activityscenarios-5db98d5311e6
     */
    @Test
    fun testActivityRecreationIfDestroyedBySystem() {
        val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

        // Wait a little bit to see the initial launch
        sleep(WAIT_TIME_BETWEEN_TEST_PHASES)

        // Now recreate the activity and wait a bit to see the result
        activityScenario.recreate()
        sleep(WAIT_TIME_BETWEEN_TEST_PHASES)
    }
}