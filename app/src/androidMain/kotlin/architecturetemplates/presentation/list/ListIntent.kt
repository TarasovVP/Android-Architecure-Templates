package architecturetemplates.presentation.list

sealed class ListIntent {
    data class LoadForks(val forkId: Long) : ListIntent()
}