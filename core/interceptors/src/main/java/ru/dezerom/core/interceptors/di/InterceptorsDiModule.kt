package ru.dezerom.core.interceptors.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dezerom.core.interceptors.AuthInterceptor
import ru.dezerom.core.tools.consts.Web
import javax.inject.Named

private const val INTERNAL_AUTH_RETROFIT = "internal_auth_retrofit"

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsDiModule {

    @Provides
    @AuthorizedRetrofit
    fun provideAuthorizedRetrofit(@Named(INTERNAL_AUTH_RETROFIT) internal: Retrofit): Retrofit =
        internal

}

@Module
@InstallIn(SingletonComponent::class)
internal object InterceptorsInternalDiModule {

    @Provides
    @AuthorizedClient
    fun provideClient(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Named(INTERNAL_AUTH_RETROFIT)
    fun provideAuthorizedRetrofit(@AuthorizedClient client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Web.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}
