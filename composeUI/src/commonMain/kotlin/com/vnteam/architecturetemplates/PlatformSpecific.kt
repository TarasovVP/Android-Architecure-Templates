package com.vnteam.architecturetemplates

import androidx.compose.ui.text.input.ImeAction

expect fun platformKeyboardOptions(imeAction: ImeAction = ImeAction.Default): Any?

expect fun platformKeyboardActions(onNext: () -> Unit = {}): Any?
