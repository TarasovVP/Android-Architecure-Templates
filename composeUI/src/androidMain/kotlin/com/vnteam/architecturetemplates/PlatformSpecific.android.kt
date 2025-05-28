package com.vnteam.architecturetemplates

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

actual fun platformKeyboardOptions(imeAction: ImeAction): Any? = KeyboardOptions(imeAction = imeAction)

actual fun platformKeyboardActions(onNext: () -> Unit): Any? = KeyboardActions(onNext = { onNext() })
