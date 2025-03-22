package ru.dezerom.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
import ru.dezerom.ui.registration.RegistrationScreenEvent
import ru.dezerom.ui.registration.RegistrationScreenState
import ru.dezerom.ui.registration.RegistrationScreenViewModel

class RegistrationViewModelTest {
    private val testMain = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUpTests() {
        Dispatchers.setMain(testMain)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitState() {
        val viewModel = RegistrationScreenViewModel(MockAuthInteractor())
        assertEquals(viewModel.getState(), RegistrationScreenState())
    }

    @Test
    fun testLoginChangedState() {
        val viewModel = RegistrationScreenViewModel(MockAuthInteractor())

        assertTrue(viewModel.getState().login.isEmpty())
        assertEquals(null, viewModel.getState().loginError)

        viewModel.onEvent(RegistrationScreenEvent.LoginChanged("asd"))
        assertEquals("asd", viewModel.getState().login)
        assertEquals(null, viewModel.getState().loginError)

        viewModel.onEvent(RegistrationScreenEvent.LoginChanged(""))
        assertTrue(viewModel.getState().login.isEmpty())
        assertNotNull(viewModel.getState().loginError)
    }

    @Test
    fun testPasswordChanged() {
        val viewModel = RegistrationScreenViewModel(MockAuthInteractor())

        assertTrue(viewModel.getState().password.isEmpty())
        assertEquals(null, viewModel.getState().passwordError)

        viewModel.onEvent(RegistrationScreenEvent.PasswordChanged("asd"))
        assertEquals("asd", viewModel.getState().password)
        assertEquals(null, viewModel.getState().passwordError)

        viewModel.onEvent(RegistrationScreenEvent.PasswordChanged(""))
        assertTrue(viewModel.getState().password.isEmpty())
        assertNotNull(viewModel.getState().passwordError)
    }

    @Test
    fun testRegistration() = runTest {
        val viewModel = RegistrationScreenViewModel(MockAuthInteractor())

        viewModel.onEvent(RegistrationScreenEvent.OnRegisterClicked)
        delay(500)
        assertNotNull(viewModel.getState().loginError)
        assertNotNull(viewModel.getState().passwordError)

        // has acc
        viewModel.onEvent(RegistrationScreenEvent.LoginChanged("qwe"))
        viewModel.onEvent(RegistrationScreenEvent.PasswordChanged("qwe"))
        viewModel.onEvent(RegistrationScreenEvent.OnRegisterClicked)
        delay(500)
        assertTrue(viewModel.getState().isLoading)
        delay(1500)

        //right credentials
        viewModel.onEvent(RegistrationScreenEvent.LoginChanged("asd"))
        viewModel.onEvent(RegistrationScreenEvent.PasswordChanged("asd"))
        viewModel.onEvent(RegistrationScreenEvent.OnRegisterClicked)
        delay(500)
        assertTrue(viewModel.getState().isLoading)
    }

    private fun RegistrationScreenViewModel.getState() = state.value
}
