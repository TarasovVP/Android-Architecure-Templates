package com.vnteam.architecturetemplates.presentation.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale

sealed class StringResources(
    var APP_NAME: String,
    var FORKS: String,
    var START: String,
    var OWNER_AVATAR: String,
    var DESCRIPTION: String,
    var NAME: String,
    var FORK: String,
    var OWNER: String,
    var URL: String,
    val BACK: String,
    var BUTTON_CANCEL: String,
    var BUTTON_OK: String
)

class StringResourcesEN : StringResources(
    APP_NAME = "Architecture Templates",
    FORKS = "Forks",
    START = "Start",
    OWNER_AVATAR = "Owner avatar",
    DESCRIPTION = "Description:",
    NAME = "Name",
    FORK = "Fork",
    OWNER = "Owner",
    URL = "URL",
    BACK = "Back",
    BUTTON_CANCEL = "Cancel",
    BUTTON_OK = "Ok"
)

class StringResourcesUK : StringResources(
    APP_NAME = "Architecture Templates",
    START = "Початок",
    FORKS = "Форки",
    OWNER_AVATAR = "Owner avatar",
    DESCRIPTION = "Опис:",
    NAME = "Ім'я",
    FORK = "Форк",
    OWNER = "Власник",
    URL = "URL",
    BACK = "Назад",
    BUTTON_CANCEL = "Відмінити",
    BUTTON_OK = "Так"
)

val LocalStringResources: ProvidableCompositionLocal<StringResources> = staticCompositionLocalOf {
    error("No StringResources provided")
}

fun getStringResourcesByLocale(locale: String): StringResources {
    return when (locale) {
        "uk" -> StringResourcesUK()
        else -> StringResourcesEN()
    }
}