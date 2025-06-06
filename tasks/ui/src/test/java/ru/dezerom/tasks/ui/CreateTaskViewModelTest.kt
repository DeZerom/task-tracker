package ru.dezerom.tasks.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.tasks.domain.TasksListInteractorMock
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.create.CreateTaskEvent
import ru.dezerom.tasks.ui.create.CreateTaskSideEffect
import ru.dezerom.tasks.ui.create.CreateTaskViewModel
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.notifiers.TasksChangeListener
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload

@OptIn(ExperimentalCoroutinesApi::class)
class CreateTaskViewModelTest {
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
        val vm = createViewModel(testMain)
        assertEquals(TaskEditingState(), vm.state.value)
    }

    @Test
    fun changeName_initiallyOk() {
        val vm = createViewModel(testMain)
        assertEquals(null, vm.state.value.nameError)
    }

    @Test
    fun changeName_success() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnNameChanged("qwe"))

        assertEquals("qwe", vm.state.value.name)
        assertNull(vm.state.value.nameError)
    }

    @Test
    fun changeName_errorAfterNotEmpty() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnNameChanged("qwe"))
        vm.onEvent(CreateTaskEvent.OnNameChanged(""))

        assertTrue(vm.state.value.nameError is StringContainer.StringRes)
        val err = vm.state.value.nameError as StringContainer.StringRes

        assertEquals(R.string.field_must_not_be_empty, err.res)
    }

    @Test
    fun changeName_okAfterError() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnNameChanged("qwe"))
        vm.onEvent(CreateTaskEvent.OnNameChanged(""))
        vm.onEvent(CreateTaskEvent.OnNameChanged("asd"))

        assertEquals("asd", vm.state.value.name)
        assertNull(vm.state.value.nameError)
    }

    @Test
    fun changeDescription_initiallyEmpty() {
        val vm = createViewModel(testMain)
        assertTrue(vm.state.value.description.isEmpty())
    }

    @Test
    fun changeDescription_success() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnDescriptionChanged("qwe"))

        assertEquals("qwe", vm.state.value.description)
    }

    @Test
    fun changeDeadline_initiallyEmpty() {
        val vm = createViewModel(testMain)
        assertNull(vm.state.value.deadline)
    }

    @Test
    fun changeDeadline_chooseDate() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnDeadlineChanged(10000L))

        assertEquals(10000L, vm.state.value.deadline)
    }

    @Test
    fun changeDeadline_clearDate() {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnDeadlineChanged(10000L))
        vm.onEvent(CreateTaskEvent.OnDeadlineChanged(null))

        assertNull(vm.state.value.deadline)
    }

    @Test
    fun addTask_notLoadingInitially() {
        val vm = createViewModel(testMain)
        assertFalse(vm.state.value.isLoading)
    }

    @Test
    fun addTask_notWorkingWithEmptyName() = runTest {
        val vm = createViewModel(testMain)
        vm.onEvent(CreateTaskEvent.OnCreateTaskClicked)
        advanceUntilIdle()

        assertTrue(vm.state.value.nameError is StringContainer.StringRes)
        val err = vm.state.value.nameError as StringContainer.StringRes
        assertEquals(R.string.field_must_not_be_empty, err.res)
    }

    @Test
    fun addTask_success() = runTest {
        val vm = createViewModel(testMain)

        val changeListener = object : TasksChangeListener {
            lateinit var triggered: TaskModel

            override fun onTasksChanged(payload: TasksChangedPayload) {
                if (payload is TasksChangedPayload.TaskAdded) {
                    triggered = payload.newTask
                }
            }
        }

        TasksChangeListenersHolder.register(changeListener)

        vm.onEvent(CreateTaskEvent.OnNameChanged("qwe"))
        vm.onEvent(CreateTaskEvent.OnDescriptionChanged("asd"))
        vm.onEvent(CreateTaskEvent.OnDeadlineChanged(1000L))
        vm.onEvent(CreateTaskEvent.OnCreateTaskClicked)
        advanceUntilIdle()

        assertEquals(TaskEditingState(), vm.state.value)
        assertEquals(vm.sideEffects.first(), CreateTaskSideEffect.DismissDialog)

        assertEquals("qwe", changeListener.triggered.name)
        assertEquals("asd", changeListener.triggered.description)
        assertEquals(1000L, changeListener.triggered.deadline)

        TasksChangeListenersHolder.unregister(changeListener)
    }

    private fun createViewModel(dispatcher: CoroutineDispatcher) =
        CreateTaskViewModel(TasksListInteractorMock(dispatcher).impl)
}