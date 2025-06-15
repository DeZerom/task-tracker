package ru.dezerom.profile.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import ru.dezerom.core.interceptors.di.AuthorizedRetrofit
import ru.dezerom.profile.data.network.ProfileApi
import ru.dezerom.profile.data.repositories.ProfileRepositoryImpl
import ru.dezerom.profile.domain.repository.ProfileRepository
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class ProfileDataDiModule {
    @Provides
    fun provideProfileRepo(@Named("internal") impl: ProfileRepository): ProfileRepository = impl
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ProfileDataInternalBindings {
    @Binds
    @Named("internal")
    abstract fun provideProfileRepo(impl: ProfileRepositoryImpl): ProfileRepository
}

@Module
@InstallIn(ViewModelComponent::class)
internal class ProfileDataInternalProviders {
    @Provides
    fun provideProfileApi(@AuthorizedRetrofit retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)
}