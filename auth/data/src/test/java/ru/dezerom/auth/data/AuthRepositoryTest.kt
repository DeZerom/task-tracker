package ru.dezerom.auth.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.MockAuthApi
import ru.dezerom.auth.data.repositories.AuthRepositoryImpl
import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.data.cache.InMemoryKeyValueCache
import ru.dezerom.core.tools.errors.NetworkError

internal class AuthRepositoryTest {
    private val repo = AuthRepositoryImpl(
        networkSource = AuthNetworkSource(MockAuthApi()),
        cacheSource = AuthCacheSource(InMemoryKeyValueCache())
    )

    @Test
    fun authorization_testValidCredentials() = runBlocking {
        val result = repo.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun authorization_testInvalidCredentials() = runBlocking {
        val result = repo.authorize("", "")
        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    fun registration_emptyCredentials() = runBlocking {
        val result = repo.register("", "")
        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    fun registration_hasSuchLogin() = runBlocking {
        val result = repo.register("qwe", "qwe")
        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    fun registration_successRegistration() = runBlocking {
        val (login, pass) = "zxc" to "zxc"

        val regResult = repo.register(login, pass)
        assertTrue(regResult.isSuccess)
        assertTrue(regResult.getOrNull() == true)

        val authResult = repo.authorize(login, pass)
        assertTrue(authResult.isSuccess)
        assertTrue(authResult.getOrNull() == true)
    }

    @Test
    fun getAuthToken_notAuthorized() = runBlocking {
        val repo = AuthRepositoryImpl(
            AuthNetworkSource(MockAuthApi()),
            AuthCacheSource(InMemoryKeyValueCache())
        )

        assertNull(repo.getAuthToken())
    }

    @Test
    fun getAuthToken_authorized() = runBlocking {
        val repo = createRepo()
        repo.authorize("qwe", "qwe")

        assertNotNull(repo.getAuthToken())
    }

    @Test
    fun unAuthorize_afterAuth_success() = runBlocking {
        val repo = createRepo()
        repo.authorize("qwe", "qwe")
        assertNotNull(repo.getAuthToken())

        repo.unAuthorize()
        assertNull(repo.getAuthToken())
    }

    @Test
    fun refreshTokens_notAuthorized() = runBlocking {
        val repo = createRepo()
        val result = repo.refreshTokens()

        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    fun refreshTokens_wrongToken() = runBlocking {
        val source = AuthCacheSource(InMemoryKeyValueCache())
        source.rememberTokens(TokensModel("", ""))

        val repo = createRepo(source)
        val result = repo.refreshTokens()

        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    fun refreshTokens_success() = runBlocking {
        val source = AuthCacheSource(InMemoryKeyValueCache())
        source.rememberTokens(TokensModel("qwerty", "qwerty"))

        val repo = createRepo(source)
        val result = repo.refreshTokens()

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun refreshTokens_successAfterLogin() = runBlocking {
        val repo = createRepo()

        repo.authorize("qwe", "qwe")
        val result = repo.refreshTokens()

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    private fun createRepo(
        cacheSource: AuthCacheSource = AuthCacheSource(InMemoryKeyValueCache())
    ) = AuthRepositoryImpl(
        AuthNetworkSource(MockAuthApi()),
        cacheSource
    )
}
