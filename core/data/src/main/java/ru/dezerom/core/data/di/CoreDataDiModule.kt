package ru.dezerom.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CoreDataDiModule {

    @Provides
    @DefaultRetrofit
    fun provideRetrofit(): Retrofit =
        baseRetrofitBuilder().build()

    private fun baseRetrofitBuilder() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.0.2.2:8080/")
}
