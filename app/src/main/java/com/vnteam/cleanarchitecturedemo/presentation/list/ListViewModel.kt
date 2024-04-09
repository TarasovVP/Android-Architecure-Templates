package com.vnteam.cleanarchitecturedemo.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI
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
    val forksFromDBLiveData = MutableLiveData<List<ForkUI>>()

    fun getForksFromApi() {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val forks = forkUseCase.getForksFromApi()
            forks?.let {
                insertForksToDB(it)
            }
            getForksFromDB()
        }
    }

    fun insertForksToDB(forks: List<Fork>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            forkUseCase.insertForksToDB(forks)
        }
    }

    fun getForksFromDB() {
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