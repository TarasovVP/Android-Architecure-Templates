package presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.vnteam.architecturetemplates.AppApplication

actual fun shareLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }
    val intentChooser = Intent.createChooser(intent, null)
    activityProvider.invoke().startActivity(intentChooser)
}

private var activityProvider: () -> Activity = {
    throw IllegalArgumentException(
        "You need to implement the 'activityProvider' to provide the required Activity. " +
                "Just make sure to set a valid activity using " +
                "the 'setActivityProvider()' method."
    )
}

fun setActivityProvider(provider: () -> Activity) {
    activityProvider = provider
}