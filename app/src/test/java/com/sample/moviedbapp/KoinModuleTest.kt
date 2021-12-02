package com.sample.moviedbapp

import com.sample.moviedbapp.datasource.getDataSourceModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModuleTest: KoinTest {
    @Test
    fun verifyKoinApp() {

        koinApplication {
            modules(getDataSourceModule())
            checkModules()
        }
    }
}