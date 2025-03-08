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
    fun testValidCredentials() = runBlocking {
        val result = repo.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun testInvalidCredentials() = runBlocking {
        val result = repo.authorize("", "")
        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }
}
