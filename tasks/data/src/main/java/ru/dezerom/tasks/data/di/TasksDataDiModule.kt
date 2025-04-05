package ru.dezerom.tasks.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import ru.dezerom.core.interceptors.di.AuthorizedRetrofit
import ru.dezerom.tasks.data.network.TasksApi
import ru.dezerom.tasks.data.repositories.TasksRepository
import ru.dezerom.tasks.data.repositories.TasksRepositoryImpl
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
data object TasksDataDiModule {
    @Provides
    fun provideRepository(@Named("internal") internal: TasksRepository): TasksRepository = internal
}

@Module
@InstallIn(ViewModelComponent::class)
internal class TasksInternalDataProvidesDiModule {
    @Provides
    fun provideTasksApi(@AuthorizedRetrofit retrofit: Retrofit): TasksApi =
        retrofit.create(TasksApi::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class TasksInternalDataBindsDiModule {
    @Binds
    @Named("internal")
    abstract fun bindRepo(impl: TasksRepositoryImpl): TasksRepository
}