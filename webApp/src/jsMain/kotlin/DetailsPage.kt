
import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Button
import org.w3c.dom.Text
import org.w3c.dom.events.Event


@Composable
fun DetailsScreen(itemId: String) {

    Button(attrs = {
        onClick {
            window.history.pushState(null, "", "/list")
            window.dispatchEvent(Event("popstate"))
        }
    }) {
        Text("Back to List")
    }
}