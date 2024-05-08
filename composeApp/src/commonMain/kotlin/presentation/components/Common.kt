package presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource

@OptIn(InternalResourceApi::class)
@Composable
fun painterRes(resId: String): Painter {
    //TODO use painterResource("drawable/${resId}.xml")
    return painterResource(DrawableResource("", setOf(ResourceItem(setOf(),"drawable/${resId}.xml", 100, 100))))
}