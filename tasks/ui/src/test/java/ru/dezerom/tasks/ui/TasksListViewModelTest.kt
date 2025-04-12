package ru.dezerom.tasks.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.dezerom.tasks.domain.TasksListUseCaseMock
import ru.dezerom.tasks.ui.list.TasksListState
import ru.dezerom.tasks.ui.list.TasksListViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class TasksList {
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
    fun init_testInitState() {
        val viewModel = createViewModel(testMain)
        assertTrue(viewModel.state.value is TasksListState.Loading)
    }

    @Test
    fun init_success() = runTest {
        val viewModel = createViewModel(testMain)
        assertTrue(viewModel.state.value is TasksListState.Loading)
        testScheduler.advanceUntilIdle()

        assertTrue(
            "${viewModel.state.value}",
            viewModel.state.value is TasksListState.Loaded
        )
        val s = viewModel.state.value as TasksListState.Loaded

        assertTrue(s.tasks.isNotEmpty())
    }

    private fun createViewModel(dispatcher: CoroutineDispatcher) =
        TasksListViewModel(TasksListUseCaseMock(dispatcher).impl)
}