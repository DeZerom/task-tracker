package ru.dezerom.auth.data

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.core.data.cache.InMemoryKeyValueCache

class AuthCacheSourceTest {

    @Test
    fun authCacheTest_empty() = runBlocking {
        val cache = AuthCacheSource(InMemoryKeyValueCache())

        val access = cache.getAccessToken()
        assertNull(access)

        val refresh = cache.getRefreshToken()
        assertNull(refresh)
    }

    @Test
    fun authCacheTest_clear() = runBlocking {
        val cache = AuthCacheSource(InMemoryKeyValueCache())
        cache.rememberTokens(TokensModel("qwe", "asd"))
        cache.clear()

        assertNull(cache.getAccessToken())
        assertNull(cache.getRefreshToken())
    }

    @Test
    fun authCacheTest_writePart() = runBlocking {
        val cache = AuthCacheSource(InMemoryKeyValueCache())
        cache.rememberTokens(TokensModel(accessToken = "qwe", refreshToken = ""))

        assertEquals("qwe", cache.getAccessToken())
        assertEquals("", cache.getRefreshToken())

        cache.clear()
        cache.rememberTokens(TokensModel("", "zxc"))

        assertEquals("", cache.getAccessToken())
        assertEquals("zxc", cache.getRefreshToken())
    }

    @Test
    fun authCacheTest_writeTokens() = runBlocking {
        val cache = AuthCacheSource(InMemoryKeyValueCache())
        val tokens = TokensModel("e1wldna", "19uifbajk")

        cache.rememberTokens(tokens)
        assertEquals(tokens.accessToken, cache.getAccessToken())
        assertEquals(tokens.refreshToken, cache.getRefreshToken())
    }

}
