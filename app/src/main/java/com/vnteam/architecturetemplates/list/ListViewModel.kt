package com.vnstudio.cleanarchitecturedemo.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.models.Fork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val forkRepository: ForkRepository
): ViewModel() {

    val progressVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val forksFromApiLiveData = MutableLiveData<List<Fork>>()
    val forksToDBInsertedLiveData = MutableLiveData<Unit>()
    val forksFromDBLiveData = MutableLiveData<List<Fork>>()

    fun getForksFromApi() {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val forks = forkRepository.getForksFromApi()
            forks?.let { forksFromApiLiveData.postValue(it) }
        }
    }

    fun insertForksToDB(forks: List<Fork>) {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            forkRepository.insertForksToDB(forks)
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
            val forks = forkRepository.getForksFromDB()
            forksFromDBLiveData.postValue(forks)
            progressVisibilityLiveData.postValue(false)
        }
    }
}