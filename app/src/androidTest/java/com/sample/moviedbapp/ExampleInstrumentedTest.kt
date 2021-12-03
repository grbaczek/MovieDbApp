package com.sample.moviedbapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sample.moviedbapp.datasource.getDataSourceModule
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        koinApplication {
            modules(module {
                single {
                    InstrumentationRegistry.getInstrumentation().targetContext
                }
            }, getDataSourceModule())
            checkModules()
        }
    }

}