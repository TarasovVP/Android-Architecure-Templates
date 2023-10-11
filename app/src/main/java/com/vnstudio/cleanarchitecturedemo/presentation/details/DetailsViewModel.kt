package com.vnstudio.cleanarchitecturedemo.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnstudio.cleanarchitecturedemo.data.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val forkRepository: ForkRepository
): ViewModel() {

    val progressVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val forkLiveData = MutableLiveData<Fork>()

    fun getForkById(forkId: Long?) {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val fork = forkRepository.getForkById(forkId ?: 0)
            forkLiveData.postValue(fork)
            progressVisibilityLiveData.postValue(false)
        }
    }
}