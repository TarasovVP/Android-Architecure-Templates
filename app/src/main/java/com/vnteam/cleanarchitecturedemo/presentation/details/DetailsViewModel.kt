package com.vnteam.cleanarchitecturedemo.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val forkRepository: DBRepository,
    private val forkUIMapper: ForkUIMapper,
): ViewModel() {

    val progressVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val forkLiveData = MutableLiveData<ForkUI>()

    fun getForkById(forkId: Long?) {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val fork = forkRepository.getForkById(forkId ?: 0)
            forkLiveData.postValue(fork?.let { forkUIMapper.mapToImplModel(it) })
            progressVisibilityLiveData.postValue(false)
        }
    }
}