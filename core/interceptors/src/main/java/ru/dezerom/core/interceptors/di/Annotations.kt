package ru.dezerom.core.interceptors.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedRetrofit
