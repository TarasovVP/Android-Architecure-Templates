package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseViewModel(
    private val screenState: MutableState<ScreenState>
) : ViewModel() {

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        showProgress(false)
        showMessage(exception.message.orEmpty(), true)
    }

    protected fun showMessage(message: String, isError: Boolean = false) {
        println("messageTAG showMessage before screenState.value.snackBarState ${screenState.value.snackBarState.snackbarVisible} message: $message")
        screenState.value = screenState.value.copy(snackBarState = screenState.value.snackBarState.copy(snackbarVisible = true, snackbarMessage = message, isSnackbarError = isError))
        println("messageTAG showMessage after screenState.value.snackBarState ${screenState.value.snackBarState.snackbarVisible} message: $message")
        viewModelScope.launch {
            delay(1000)
            screenState.value = screenState.value.copy(snackBarState = screenState.value.snackBarState.copy(snackbarVisible = false, snackbarMessage = ""))
            println("messageTAG showMessage delay(1000) after screenState.value.snackBarState ${screenState.value.snackBarState.snackbarVisible} message: $message")
        }
    }

    protected fun showProgress(isShow: Boolean) {
        screenState.value = screenState.value.copy(isProgressVisible = isShow)
    }
}