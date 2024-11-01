package com.josemiz.cityseeker.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josemiz.cityseeker.data.local.AppDatabase
import com.josemiz.cityseeker.data.local.CityDao
import com.josemiz.cityseeker.data.remote.CityService
import com.josemiz.cityseeker.data.repository.CityRepositoryImpl
import com.josemiz.cityseeker.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/hernan-uala/dce8843a8edbe0b0018b32e137bc2b3a/raw/0996accf70cb0ca0e16f9a99e0ee185fafca7af1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCityService(retrofit: Retrofit): CityService =
        retrofit.create(CityService::class.java)

    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "CityDatabase"
    ).build()

    @Provides
    fun provideCityDao(
        appDatabase: AppDatabase
    ): CityDao = appDatabase.cityDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ): CityRepository
}