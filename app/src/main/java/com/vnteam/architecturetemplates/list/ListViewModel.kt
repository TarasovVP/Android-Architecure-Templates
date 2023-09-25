package com.vnstudio.cleanarchitecturedemo.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORKS_URL
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.models.Fork
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val valleyApiConnector: ValleyApiConnector,
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
            valleyApiConnector.makeRequest(FORKS_URL, { forks ->
                progressVisibilityLiveData.postValue(false)
                forksFromApiLiveData.postValue(forks)
            }, { error ->
                errorLiveData.postValue(error)
            })
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