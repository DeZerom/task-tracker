package ru.dezerom.auth.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.network.AuthMockApi
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.tools.errors.NetworkError

internal class AuthNetworkSourceTest {
    private val source = AuthNetworkSource(AuthMockApi())

    @Test
    fun testValidCredentials() = runBlocking {
        val result = source.authorize("qwe", "qwe")
        assertTrue(result.isSuccess)

        val tokens = result.getOrNull()
        assertNotNull(tokens)
        assertFalse(tokens?.accessToken.isNullOrBlank())
        assertFalse(tokens?.refreshToken.isNullOrBlank())
    }

    @Test
    fun testInvalidCredentials() = runBlocking {
        val result = source.authorize("", "")
        assertFalse(result.isSuccess)

        val exception = result.exceptionOrNull()
        assertTrue(exception is NetworkError)
    }
}