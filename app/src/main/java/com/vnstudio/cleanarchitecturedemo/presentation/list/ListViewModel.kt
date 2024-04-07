package com.vnstudio.cleanarchitecturedemo.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork
import com.vnstudio.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnstudio.cleanarchitecturedemo.presentation.uimodels.ForkUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val forkUseCase: ForkUseCase,
    private val forkUIMapper: ForkUIMapper,
) : ViewModel() {

    val progressVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val forksFromApiLiveData = MutableLiveData<List<Fork>>()
    val forksToDBInsertedLiveData = MutableLiveData<Unit>()
    val forksFromDBLiveData = MutableLiveData<List<ForkUI>>()

    fun getForksFromApi() {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val forks = forkUseCase.getForksFromApi()
            forks?.let { forksFromApiLiveData.postValue(it) }
        }
    }

    fun insertForksToDB(forks: List<Fork>) {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            forkUseCase.insertForksToDB(forks)
            progressVisibilityLiveData.postValue(false)
            forksToDBInsertedLiveData.postValue(Unit)
        }
    }

    fun getForksFromDB() {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val forks = forkUIMapper.mapToImplModelList(forkUseCase.getForksFromDB())
            forksFromDBLiveData.postValue(forks)
            progressVisibilityLiveData.postValue(false)
        }
    }
}