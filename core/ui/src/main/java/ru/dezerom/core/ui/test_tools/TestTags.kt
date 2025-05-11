package ru.dezerom.core.ui.test_tools

object TestTags {
    object TextInput {
        const val LOGIN = "login_input"
        const val PASSWORD = "password_input"

        const val ERROR_HINT_POSTFIX = "_error_hint"

        fun errorFor(fieldFactory: TextInput.() -> String) =
            "${fieldFactory(TextInput)}$ERROR_HINT_POSTFIX"
    }

    object Image {
        const val APP_ICON = "app_icon"
        const val WARNING_ICON = "warning_icon"
    }

    object Button {
        const val CREATE_ACC_BUTTON = "create_acc_button"
        const val AUTH_BUTTON = "auth_button"

        const val TRY_AGAIN_BUTTON = "try_again_button"

        const val BUTTON_LOADER_POSTFIX = "_btn_loader"
        const val BUTTON_TEXT_POSTFIX = "_btn_text"

        fun loaderFor(fieldFactory: Button.() -> String) =
            "${fieldFactory(Button)}$BUTTON_LOADER_POSTFIX"

        fun textFor(fieldFactory: Button.() -> String) =
            "${fieldFactory(Button)}$BUTTON_TEXT_POSTFIX"
    }

    object Snackbar {
        const val SUCCESS_SNACKBAR = "success_snackbar"
        const val ERROR_SNACKBAR = "error_snackbar"

        const val SNACKBAR_TEXT_POSTFIX = "_snackbar_text"

        fun textFor(fieldFactory: Snackbar.() -> String) =
            "${fieldFactory(Snackbar)}$SNACKBAR_TEXT_POSTFIX"
    }

    object Objects {
        const val LOADER = "loader"

        const val CHECK_BOX = "check_box"
    }

    object Components {
        const val LOADER_COMPONENT = "loader_component"
        const val ERROR_COMPONENT = "error_component"
    }
}
