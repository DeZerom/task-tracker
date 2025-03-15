package ru.dezerom.auth.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.repositories.MockAuthRepository
import ru.dezerom.auth.domain.interactor.AuthInteractorImpl

internal class AuthInteractorImplTest {
    private val interactor = AuthInteractorImpl(MockAuthRepository())

    @Test
    fun authorization_testAuth() = runBlocking {
        val result = interactor.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun authorization_testWrongAuth() = runBlocking {
        val result = interactor.authorize("", "")
        assertFalse(result.isSuccess)
        assertFalse(result.getOrNull() == true)
    }

    @Test
    fun registration_testEmptyCredentials() = runBlocking {
        val result = interactor.register("", "")
        assertFalse(result.isSuccess)
        assertFalse(result.getOrNull() == true)
    }

    @Test
    fun registration_testHasSuchLogin() = runBlocking {
        val result = interactor.register("qwe", "qwe")
        assertFalse(result.isSuccess)
        assertFalse(result.getOrNull() == true)
    }

    @Test
    fun registration_testSuccess() = runBlocking {
        val (login, pass) = "ghj" to "ghj"

        val regResult = interactor.register(login, pass)
        assertTrue(regResult.isSuccess)
        assertTrue(regResult.getOrNull() == true)

        val authResult = interactor.authorize(login, pass)
        assertTrue(authResult.isSuccess)
        assertTrue(authResult.getOrNull() == true)
    }
}