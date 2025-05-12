package ru.dezerom.core.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.dezerom.auth.data.repositories.AuthRepository
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor(
    private val authRepository: AuthRepository,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = runBlocking { sendRequest(chain) }

        if (response.code() != 401) {
            return response
        } else {
            response.close()
        }

        val result = runBlocking { authRepository.refreshTokens() }
        return result.fold(
            onSuccess = {
                if (it) {
                    runBlocking { sendRequest(chain) }
                } else {
                    runBlocking { authRepository.unAuthorize() }
                    createUnAuthResponse()
                }
            },
            onFailure = {
                runBlocking { authRepository.unAuthorize() }
                createUnAuthResponse()
            }
        )
    }

    private suspend fun sendRequest(chain: Interceptor.Chain): Response {
        val token = authRepository.getAuthToken() ?: return createUnAuthResponse()

        val headers = chain.request().headers().newBuilder()
            .add(AUTH_HEADER, "Bearer $token")
            .build()

        val request = chain.request().newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(request)
    }

    private fun createUnAuthResponse() = Response.Builder()
        .code(401)
        .build()

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}
