package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val appUseCase: AppUseCase
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val _language = MutableStateFlow<String?>(null)
    val language: StateFlow<String?> = _language.asStateFlow()

    init {
        getIsDarkTheme()
        getLanguage()
    }

    private fun getIsDarkTheme() {
        viewModelScope.launch {
            _isDarkTheme.value = appUseCase.getIsDarkTheme()
        }
    }

    private fun getLanguage() {
        viewModelScope.launch {
            _language.value = appUseCase.getLanguage()
        }
    }
}