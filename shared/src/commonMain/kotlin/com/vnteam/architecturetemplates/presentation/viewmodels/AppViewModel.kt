package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val appUseCase: AppUseCase
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow<Boolean?>(null)
    val isDarkTheme: StateFlow<Boolean?> = _isDarkTheme.asStateFlow()

    private val _language = MutableStateFlow<String?>(null)
    val language: StateFlow<String?> = _language.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->

    }

    init {
        getIsDarkTheme()
        getLanguage()
    }

    private fun getIsDarkTheme() {
        viewModelScope.launch(exceptionHandler) {
            appUseCase.getIsDarkTheme().collect {
                _isDarkTheme.value = it
            }
        }
    }

    fun setIsDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch(exceptionHandler) {
            appUseCase.setIsDarkTheme(isDarkTheme)
        }
    }

    private fun getLanguage() {
        viewModelScope.launch(exceptionHandler) {
            appUseCase.getLanguage().collect {
                _language.value = it
            }
        }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch(exceptionHandler) {
            appUseCase.setLanguage(language)
        }
    }
}