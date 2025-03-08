package ru.dezerom.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.dezerom.auth.domain.interactor.MockAuthInteractor
import ru.dezerom.ui.auth.AuthScreenEvent
import ru.dezerom.ui.auth.AuthScreenSideEffect
import ru.dezerom.ui.auth.AuthScreenState
import ru.dezerom.ui.auth.AuthViewModel

@OptIn(ExperimentalCoroutinesApi::class)
internal class AuthViewModelTest {
    private val testMain = StandardTestDispatcher()

    @Before
    fun setUpTests() {
        Dispatchers.setMain(testMain)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitState() {
        val viewModel = AuthViewModel(MockAuthInteractor())
        assertEquals(viewModel.getState(), AuthScreenState())
    }

    @Test
    fun testLoginChangedState() {
        val viewModel = AuthViewModel(MockAuthInteractor())

        assertTrue(viewModel.getState().login.isEmpty())
        assertEquals(null, viewModel.getState().loginError)

        viewModel.onEvent(AuthScreenEvent.LoginChanged("asd"))
        assertEquals("asd", viewModel.getState().login)
        assertEquals(null, viewModel.getState().loginError)

        viewModel.onEvent(AuthScreenEvent.LoginChanged(""))
        assertTrue(viewModel.getState().login.isEmpty())
        assertNotNull(viewModel.getState().loginError)
    }

    @Test
    fun testPasswordChanged() {
        val viewModel = AuthViewModel(MockAuthInteractor())

        assertTrue(viewModel.getState().password.isEmpty())
        assertEquals(null, viewModel.getState().passwordError)

        viewModel.onEvent(AuthScreenEvent.PasswordChanged("asd"))
        assertEquals("asd", viewModel.getState().password)
        assertEquals(null, viewModel.getState().passwordError)

        viewModel.onEvent(AuthScreenEvent.PasswordChanged(""))
        assertTrue(viewModel.getState().password.isEmpty())
        assertNotNull(viewModel.getState().passwordError)
    }

    @Test
    fun testGoToRegistration() = runTest {
        val viewModel = AuthViewModel(MockAuthInteractor())

        viewModel.onEvent(AuthScreenEvent.OnCreateAccClicked)
        assertTrue(viewModel.sideEffect.first() is AuthScreenSideEffect.GoToRegistration)
    }

    @Test
    fun testAuth() = runTest {
        val viewModel = AuthViewModel(MockAuthInteractor())

        viewModel.onEvent(AuthScreenEvent.OnAuthorizeClicked)
        delay(500)
        assertNotNull(viewModel.getState().loginError)
        assertNotNull(viewModel.getState().passwordError)

        // wrong credentials
        viewModel.onEvent(AuthScreenEvent.LoginChanged("asd"))
        viewModel.onEvent(AuthScreenEvent.PasswordChanged("asd"))
        viewModel.onEvent(AuthScreenEvent.OnAuthorizeClicked)
        delay(500)
        assertTrue(viewModel.getState().isLoading)
        delay(1500)

        //right credentials
        viewModel.onEvent(AuthScreenEvent.LoginChanged("qwe"))
        viewModel.onEvent(AuthScreenEvent.PasswordChanged("qwe"))
        viewModel.onEvent(AuthScreenEvent.OnAuthorizeClicked)
        delay(500)
        assertTrue(viewModel.getState().isLoading)
    }

    private fun AuthViewModel.getState() = state.value
}