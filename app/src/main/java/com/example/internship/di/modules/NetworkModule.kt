package com.example.internship.di.modules

import com.example.internship.data.api.InternshipApi
import com.example.internship.util.Constants
import com.example.internship.util.Constants.INTERNSHIP_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideInternshipApi():InternshipApi{
        return Retrofit.Builder()
            .baseUrl(INTERNSHIP_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InternshipApi::class.java)
    }
}