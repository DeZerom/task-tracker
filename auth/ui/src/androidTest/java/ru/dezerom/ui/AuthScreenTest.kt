package ru.dezerom.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.dezerom.auth.domain.interactor.MockAuthInteractor
import ru.dezerom.core.tools.R
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.ui.auth.AuthScreenContent
import ru.dezerom.ui.auth.AuthScreenState
import ru.dezerom.ui.auth.AuthViewModel

@RunWith(AndroidJUnit4::class)
internal class AuthScreenTest {

    @get:Rule val testRule = createComposeRule()

    @Test
    fun authScreenTest_initState() {
        testRule.setContent {
            TaskTrackerTheme {
                AuthScreenContent(
                    state = AuthScreenState(),
                    onEvent = {},
                    snackbarHostState = SnackbarHostState()
                )
            }
        }

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.onNodeWithTag(TestTags.Image.APP_ICON).assertIsDisplayed()

        testRule.onNodeWithTag(TestTags.TextInput.LOGIN).apply {
            assertIsDisplayed()
            assertTextEquals(context.getString(R.string.login), "")
        }

        testRule.onNodeWithTag(TestTags.TextInput.PASSWORD).apply {
            assertIsDisplayed()
            assertTextEquals(context.getString(R.string.password), "")
        }

        testRule.onNodeWithTag(TestTags.Button.CREATE_ACC_BUTTON).assertIsDisplayed()

        testRule.onNodeWithTag(TestTags.Button.AUTH_BUTTON)
            .assertIsDisplayed()
            .assertTextEquals(context.getString(R.string.authorize))
    }

    @Test
    fun authScreenTest_writeLoginAndPassword() {
        val viewModel = AuthViewModel(MockAuthInteractor())
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            TaskTrackerTheme {
                val state = viewModel.state.collectAsState()

                AuthScreenContent(
                    state = state.value,
                    onEvent = viewModel::onEvent,
                    snackbarHostState = SnackbarHostState()
                )
            }
        }

        testRule.onNodeWithTag(TestTags.TextInput.LOGIN).apply {
            performTextInput("123")
            assertTextEquals(context.getString(R.string.login), "123")
        }

        testRule.onNodeWithTag(TestTags.TextInput.PASSWORD).apply {
            performTextInput("456")
            assertTextEquals(context.getString(R.string.password), "***")
        }
    }

    @Test
    fun authScreenTest_emptyAuthWithEmptyFields() {
        val viewModel = AuthViewModel(MockAuthInteractor())
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            TaskTrackerTheme {
                val state = viewModel.state.collectAsState()

                AuthScreenContent(
                    state = state.value,
                    onEvent = viewModel::onEvent,
                    snackbarHostState = SnackbarHostState()
                )
            }
        }

        testRule.onNodeWithTag(TestTags.Button.AUTH_BUTTON).performClick()

        testRule.onNodeWithTag(TestTags.TextInput.run { "$LOGIN$ERROR_HINT_POSTFIX" }).apply {
            assertIsDisplayed()
            assertTextEquals(context.getString(R.string.field_must_not_be_empty))
        }

        testRule.onNodeWithTag(TestTags.TextInput.run { "$PASSWORD$ERROR_HINT_POSTFIX" }).apply {
            assertIsDisplayed()
            assertTextEquals(context.getString(R.string.field_must_not_be_empty))
        }
    }

    @Test
    fun authScreenTest_emptyFieldAfterFull() {
        val viewModel = AuthViewModel(MockAuthInteractor())

        testRule.setContent {
            TaskTrackerTheme {
                val state = viewModel.state.collectAsState()

                AuthScreenContent(
                    state = state.value,
                    onEvent = viewModel::onEvent,
                    snackbarHostState = SnackbarHostState()
                )
            }
        }

        val loginError = testRule.onNodeWithTag(TestTags.TextInput.errorFor { LOGIN })
        val login = testRule.onNodeWithTag(TestTags.TextInput.LOGIN)

        loginError.assertDoesNotExist()
        login.performTextInput("123")
        loginError.assertDoesNotExist()
        login.performTextClearance()
        loginError.assertIsDisplayed()

        val passwordError = testRule.onNodeWithTag(TestTags.TextInput.errorFor { PASSWORD })
        val password = testRule.onNodeWithTag(TestTags.TextInput.PASSWORD)

        passwordError.assertDoesNotExist()
        password.performTextInput("***")
        passwordError.assertDoesNotExist()
        password.performTextClearance()
        passwordError.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun authScreenTest_wrongCredentials() {
        val viewModel = AuthViewModel(MockAuthInteractor())
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            TaskTrackerTheme {
                val state = viewModel.state.collectAsState()

                AuthScreenContent(
                    state = state.value,
                    onEvent = viewModel::onEvent,
                    snackbarHostState = viewModel.snackbarHostState
                )
            }
        }

        testRule.onNodeWithTag(TestTags.TextInput.LOGIN).performTextInput("asd")
        testRule.onNodeWithTag(TestTags.TextInput.PASSWORD).performTextInput("asd")
        testRule.onNodeWithTag(TestTags.Button.AUTH_BUTTON).performClick()
        testRule.onNodeWithTag(TestTags.Button.loaderFor { AUTH_BUTTON }).assertIsDisplayed()
        testRule.onNodeWithTag(TestTags.Button.textFor { AUTH_BUTTON }).assertDoesNotExist()

        testRule.waitUntilDoesNotExist(
            timeoutMillis = 1100,
            matcher = SemanticsMatcher("no loader") {
                it.testTag == TestTags.Button.loaderFor { AUTH_BUTTON }
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1150,
            matcher = SemanticsMatcher("btn has text") {
                it.testTag == TestTags.Button.AUTH_BUTTON
                        && it.joinedText == context.getString(R.string.authorize)
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1100,
            matcher = SemanticsMatcher("has snackbar") {
                it.testTag == TestTags.Snackbar.ERROR_SNACKBAR
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1150,
            matcher = SemanticsMatcher("snackbar text is not empty") {
                it.testTag == TestTags.Snackbar.textFor { ERROR_SNACKBAR }
                        && it.joinedText.isNullOrEmpty().not()
            }
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun authScreenTest_successAuthorization() {
        val viewModel = AuthViewModel(MockAuthInteractor())
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            TaskTrackerTheme {
                val state = viewModel.state.collectAsState()

                AuthScreenContent(
                    state = state.value,
                    onEvent = viewModel::onEvent,
                    snackbarHostState = viewModel.snackbarHostState
                )
            }
        }

        testRule.onNodeWithTag(TestTags.TextInput.LOGIN).performTextInput("qwe")
        testRule.onNodeWithTag(TestTags.TextInput.PASSWORD).performTextInput("qwe")
        testRule.onNodeWithTag(TestTags.Button.AUTH_BUTTON).performClick()
        testRule.onNodeWithTag(TestTags.Button.loaderFor { AUTH_BUTTON }).assertIsDisplayed()
        testRule.onNodeWithTag(TestTags.Button.textFor { AUTH_BUTTON }).assertDoesNotExist()

        testRule.waitUntilDoesNotExist(
            timeoutMillis = 1100,
            matcher = SemanticsMatcher("no loader") {
                it.testTag == TestTags.Button.loaderFor { AUTH_BUTTON }
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1150,
            matcher = SemanticsMatcher("btn has text") {
                it.testTag == TestTags.Button.AUTH_BUTTON
                        && it.joinedText == context.getString(R.string.authorize)
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1100,
            matcher = SemanticsMatcher("has snackbar") {
                it.testTag == TestTags.Snackbar.SUCCESS_SNACKBAR
            }
        )
        testRule.waitUntilExactlyOneExists(
            timeoutMillis = 1150,
            matcher = SemanticsMatcher("snackbar text is not empty") {
                it.testTag == TestTags.Snackbar.textFor { SUCCESS_SNACKBAR }
                        && it.joinedText.isNullOrEmpty().not()
            }
        )
    }

    private val SemanticsNode.testTag: String?
        get() = config.getOrNull(SemanticsProperties.TestTag)

    private val SemanticsNode.joinedText: String?
        get() = config.getOrNull(SemanticsProperties.Text)?.joinToString()
}
