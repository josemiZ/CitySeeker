package com.josemiz.cityseeker.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.josemiz.cityseeker.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class CityDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var cityDao: CityDao
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        cityDao = database.cityDao()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun closeDatabase() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `Add city and verify it afterwards`() = runTest {
        val city = City(1, "Lima", "PE", -1234.0,2131.0, false)
        cityDao.insertAll(city)
        advanceUntilIdle()
        val list = cityDao.getCities()
        advanceUntilIdle()
        assert(list.contains(city))
    }
}