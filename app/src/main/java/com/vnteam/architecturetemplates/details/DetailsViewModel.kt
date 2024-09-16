<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
package com.vnteam.architecturetemplates.presentation.details
========
package com.vnteam.architecturetemplates.details
>>>>>>>> 13d1264 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/details/DetailsViewModel.kt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
import com.vnteam.architecturetemplates.domain.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
========
import com.vnteam.architecturetemplates.database.ForkRepository
import com.vnteam.architecturetemplates.models.Fork
>>>>>>>> 13d1264 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/details/DetailsViewModel.kt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
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
            forkLiveData.postValue(forkUIMapper.mapToImplModel(fork))
            progressVisibilityLiveData.postValue(false)
        }
    }
}