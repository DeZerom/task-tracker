package ru.dezerom.auth.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import ru.dezerom.auth.data.network.AuthApi
import ru.dezerom.auth.data.repositories.AuthRepository
import ru.dezerom.auth.data.repositories.AuthRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AuthModuleDi {
    @Provides
    fun provideRepo(@Named("Internal") internal: AuthRepository): AuthRepository = internal
}

@Module
@InstallIn(SingletonComponent::class)
internal object InternalAuthModuleDi {
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class InternalAuthRepoModuleDi {
    @Binds
    @Named("Internal")
    abstract fun bindRepo(impl: AuthRepositoryImpl): AuthRepository
}
