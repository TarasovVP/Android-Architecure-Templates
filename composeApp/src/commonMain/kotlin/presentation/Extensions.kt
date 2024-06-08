package presentation

import androidx.compose.runtime.Composable
import com.vnteam.architecturetemplates.presentation.resources.getStringResources

@Composable
fun String?.textWithNoDataHandling() = this.takeIf { it.orEmpty().isNotEmpty() } ?: getStringResources().NO_DATA