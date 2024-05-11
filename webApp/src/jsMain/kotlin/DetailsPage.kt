import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.details.DetailsIntent
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import kotlinx.browser.window
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexShrink
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.koin.compose.koinInject
import org.w3c.dom.events.Event
import presentation.details.DetailsViewModel
import resources.getStringResources


@Composable
fun DetailsScreen(itemId: String) {
    Style(AppStyles)
    val viewModel = koinInject<DetailsViewModel>()

    val detailsViewStateState = viewModel.state.collectAsState()
    val error = mutableStateOf(viewModel.state.value.error)

    LaunchedEffect(itemId) {
        viewModel.processIntent(DetailsIntent.LoadFork(itemId.toLong()))
    }

    LaunchedEffect(detailsViewStateState.value) {
        error.value = viewModel.state.value.error
    }
    console.log("DetailsScreen ${detailsViewStateState.value.fork?.name}")
    VerticalLayout {
        Div(attrs = { classes(AppStyles.descriptionStyle) }) { Text(detailsViewStateState.value.fork?.name.orEmpty()) }
        Div(attrs = { classes(AppStyles.descriptionStyle) }) { Text(detailsViewStateState.value.fork?.owner?.login.orEmpty()) }
        OwnerCard(detailsViewStateState.value.fork?.owner)
        Div(attrs = { classes(AppStyles.descriptionStyle) }) { Text("Description:") }
        Div(attrs = { classes(AppStyles.descriptionStyle) }) { Text(detailsViewStateState.value.fork?.description.orEmpty()) }
        BaseButton(getStringResources().BACK) {
            window.history.pushState(null, "", "/list")
            window.dispatchEvent(Event("popstate"))
        }

        if (viewModel.state.value.isLoading) {
            CircularProgress()
        }
    }
}

@Composable
fun OwnerCard(owner: OwnerUI?) {
    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
            gap(30.px)
            width(1110.px)
            padding(16.px)
            alignItems(AlignItems.Center)
            flexShrink(0)
            borderRadius(16.px)
            justifyContent(JustifyContent.Center)
            backgroundColor(Color("rgba(240, 240, 240, 1)"))
        }
    }) {
        Img(src = owner?.avatarUrl.orEmpty(),
            alt = "img210",
            attrs = { style {
                width(60.px)
                height(60.px)
            }
            }
        )
        Div(attrs = { classes( AppStyles.descriptionStyle ) }) { Text(owner?.login.orEmpty()) }
    }
}