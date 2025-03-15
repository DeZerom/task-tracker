package ru.dezerom.auth.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.network.MockAuthApi
import ru.dezerom.auth.data.repositories.AuthRepositoryImpl
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.tools.errors.NetworkError

internal class AuthRepositoryTest {
    private val repo = AuthRepositoryImpl(AuthNetworkSource(MockAuthApi()))

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
}
