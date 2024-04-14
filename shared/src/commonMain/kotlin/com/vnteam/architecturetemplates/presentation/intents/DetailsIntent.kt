<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/intents/DetailsIntent.kt
package com.vnteam.architecturetemplates.presentation.intents
========
package architecturetemplates.presentation.details
>>>>>>>> afd887b0 (Implement ios module):app/src/androidMain/kotlin/architecturetemplates/presentation/details/DetailsIntent.kt

sealed class DetailsIntent {
    data class LoadFork(val forkId: Long) : DetailsIntent()
}