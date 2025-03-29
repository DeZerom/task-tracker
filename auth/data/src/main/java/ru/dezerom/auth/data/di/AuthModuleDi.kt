package ru.dezerom.auth.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.create
import ru.dezerom.auth.data.network.AuthApi
import ru.dezerom.auth.data.repositories.AuthRepository
import ru.dezerom.auth.data.repositories.AuthRepositoryImpl
import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.core.data.cache.DataStoreKeyValueCache
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object AuthModuleDi {
    @Provides
    fun provideRepo(@Named("Internal") internal: AuthRepository): AuthRepository = internal
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class InternalAuthScopedModuleDi {
    @Binds
    @Named("Internal")
    abstract fun bindRepo(impl: AuthRepositoryImpl): AuthRepository
}

@Module
@InstallIn(ViewModelComponent::class)
internal class InternalAuthProvidesScopedModuleDi {
    private val Context.store by preferencesDataStore("auth_prefs")

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create()
    }

    @Provides
    fun provideAuthCacheSource(@ApplicationContext context: Context): AuthCacheSource {
        return AuthCacheSource(
            DataStoreKeyValueCache(store = context.store)
        )
    }
}
