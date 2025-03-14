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
    fun testAuth() = runBlocking {
        val result = interactor.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun testWrongAuth() = runBlocking {
        val result = interactor.authorize("", "")
        assertFalse(result.isSuccess)
    }
}