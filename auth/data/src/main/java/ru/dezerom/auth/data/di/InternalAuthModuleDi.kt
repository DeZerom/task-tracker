package ru.dezerom.auth.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create
import ru.dezerom.auth.data.network.AuthApi

@Module
@InstallIn(ViewModelComponent::class)
internal interface InternalAuthModuleDi {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create()
    }

}
