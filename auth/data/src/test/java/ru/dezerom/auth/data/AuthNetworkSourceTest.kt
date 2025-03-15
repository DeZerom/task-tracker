package ru.dezerom.auth.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.network.MockAuthApi
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.tools.errors.NetworkError

internal class AuthNetworkSourceTest {
    private val source = AuthNetworkSource(MockAuthApi())

    @Test
    fun authorization_testValidCredentials() = runBlocking {
        val result = source.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)

        val tokens = result.getOrNull()
        assertNotNull(tokens)
        assertFalse(tokens?.accessToken.isNullOrBlank())
        assertFalse(tokens?.refreshToken.isNullOrBlank())
    }

    @Test
    fun authorization_testInvalidCredentials() = runBlocking {
        val result = source.authorize("", "")
        assertFalse(result.isSuccess)

        val exception = result.exceptionOrNull()
        assertTrue(exception is NetworkError)
    }

    @Test
    fun registration_testEmptyCredentials() = runBlocking {
        val result = source.register("", "")
        assertFalse(result.isSuccess)

        val exception = result.exceptionOrNull()

        assertTrue(exception is NetworkError)
    }

    @Test
    fun registration_testHasSuchLogin() = runBlocking {
        val result = source.register("", "")
        assertFalse(result.isSuccess)

        val exception = result.exceptionOrNull()

        assertTrue(exception is NetworkError)
    }

    @Test
    fun registration_testSuccess() = runBlocking {
        val (login, pass) = "asd" to "asd"

        val result = source.register(login, pass)
        assertTrue(result.isSuccess)

        val body = result.getOrNull() == true
        assertTrue(body)

        val authResult = source.authorize(login, pass)
        assertTrue(authResult.isSuccess)

        val authBody = authResult.getOrNull()
        assertNotNull(authBody)
        authBody!!

        assertTrue(authBody.accessToken.isNotBlank())
        assertTrue(authBody.refreshToken.isNotBlank())
    }
}