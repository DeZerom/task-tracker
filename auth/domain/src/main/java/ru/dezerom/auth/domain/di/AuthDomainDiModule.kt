package ru.dezerom.auth.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.dezerom.auth.domain.interactor.AuthInteractor
import ru.dezerom.auth.domain.interactor.AuthInteractorImpl
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class AuthDomainDiModule {
    @Provides
    fun provideInteractor(@Named("internal") internal: AuthInteractor): AuthInteractor = internal
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class InternalAuthDomainDiModule {
    @Binds
    @Named("internal")
    abstract fun bindInteractor(impl: AuthInteractorImpl): AuthInteractor
}
