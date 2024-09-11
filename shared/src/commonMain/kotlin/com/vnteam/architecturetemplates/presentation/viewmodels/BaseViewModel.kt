package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel(
    private val screenState: MutableState<ScreenState>
) : ViewModel() {

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        showProgress(false)
        showMessage(exception.message.orEmpty(), true)
    }

    protected fun showMessage(message: String, isError: Boolean = false) {
        screenState.value = screenState.value.copy(appMessageState = screenState.value.appMessageState.copy(messageVisible = true, messageText = message, isMessageError = isError))
    }

    protected fun showProgress(isShow: Boolean) {
        screenState.value = screenState.value.copy(isProgressVisible = isShow)
    }
}