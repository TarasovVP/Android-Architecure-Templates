import Constants.PATH_SEPARATOR
import Constants.POP_STATE
import org.w3c.dom.Window
import org.w3c.dom.events.Event

fun Window.isMainScreen(): Boolean {
    return window.location.pathname == PATH_SEPARATOR
}

fun Window.navigateTo(path: String) {
    history.pushState(null, "", path)
    dispatchEvent(Event(POP_STATE))
}

fun Window.navigateUp() {
    history.back()
}

fun Window.navigateToMain() {
    history.replaceState(null, "", PATH_SEPARATOR)
    dispatchEvent(Event(POP_STATE))
}