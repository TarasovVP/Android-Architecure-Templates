package com.vnteam.architecturetemplates.presentation.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale

sealed class StringResources(
    var APP_NAME: String,
    var START: String,
    var OWNER_AVATAR: String,
    var DESCRIPTION: String,
    var NAME: String,
    var FORK: String,
    var OWNER: String,
    var URL: String,
    val BACK: String,
    var ADD: String,
    var DELETE: String,
    var BUTTON_CANCEL: String,
    var BUTTON_OK: String,
    var CREATE: String,
    var EDIT: String,
    val EMPTY_STATE: String
)

class StringResourcesEN : StringResources(
    APP_NAME = "Architecture Templates",
    START = "Start",
    OWNER_AVATAR = "Owner avatar",
    DESCRIPTION = "Description:",
    NAME = "Name",
    FORK = "Fork",
    OWNER = "Owner",
    URL = "URL",
    BACK = "Back",
    ADD = "Add",
    DELETE = "Delete",
    BUTTON_CANCEL = "Cancel",
    BUTTON_OK = "Ok",
    CREATE = "Create",
    EDIT = "Edit",
    EMPTY_STATE = "No data"
)

class StringResourcesUK : StringResources(
    APP_NAME = "Architecture Templates",
    START = "Початок",
    OWNER_AVATAR = "Owner avatar",
    DESCRIPTION = "Опис:",
    NAME = "Ім'я",
    FORK = "Форк",
    OWNER = "Власник",
    URL = "URL",
    BACK = "Назад",
    ADD = "Добавить",
    DELETE = "Видалити",
    BUTTON_CANCEL = "Відмінити",
    BUTTON_OK = "Так",
    CREATE = "Створити",
    EDIT = "Редагувати",
    EMPTY_STATE = "Немає даних"
)

fun getStringResourcesByLocale(locale: String): StringResources {
    return when (locale) {
        "uk" -> StringResourcesUK()
        else -> StringResourcesEN()
    }
}

@Composable
fun getStringResources(): StringResources {
    val locale = Locale.current.language
    return getStringResourcesByLocale(locale)
}