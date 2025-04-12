package ru.dezerom.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dezerom.core.tools.consts.Web

@Module
@InstallIn(SingletonComponent::class)
object CoreDataDiModule {

    @Provides
    @IODispatcher
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DefaultRetrofit
    fun provideRetrofit(): Retrofit =
        baseRetrofitBuilder().build()

    private fun baseRetrofitBuilder() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Web.BASE_URL)
}
