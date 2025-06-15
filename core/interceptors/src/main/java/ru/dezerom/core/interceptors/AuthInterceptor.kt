package ru.dezerom.core.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.dezerom.auth.data.repositories.AuthRepository
import ru.dezerom.core.tools.errors.unAuthorizedNetworkError
import ru.dezerom.profile.domain.dispatchers.LogoutDispatcher
import ru.dezerom.profile.domain.dispatchers.LogoutEvents
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor(
    private val authRepository: AuthRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val token = authRepository.getAuthToken() ?: throw unAuthorizedNetworkError()

            val response = sendRequest(chain, token)
            if (response.code() == 401) {
                response.close()
            } else {
                return@runBlocking response
            }

            authRepository.refreshTokens().fold(
                onSuccess = {
                    if (it) {
                        val newToken = authRepository.getAuthToken() ?: throw unAuthorizedNetworkError()
                        return@runBlocking sendRequest(chain, newToken)
                    } else {
                        logout()
                        throw unAuthorizedNetworkError()
                    }
                },
                onFailure = {
                    logout()
                    throw unAuthorizedNetworkError()
                }
            )
        }
    }

    private fun sendRequest(chain: Interceptor.Chain, token: String): Response {
        val headers = chain.request().headers().newBuilder()
            .add(AUTH_HEADER, "Bearer $token")
            .build()

        val request = chain.request().newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(request)
    }

    private suspend fun logout() {
        authRepository.unAuthorize()
        LogoutDispatcher.dispatchEvent(LogoutEvents.Logout)
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}
