package com.vnteam.architecturetemplates.shared

import com.vnteam.architecturetemplates.presentation.TextToSpeechHelper

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class TextToSpeechHelper {
    private var textToSpeechHelper = TextToSpeechHelper()

    actual fun speak(text: String) {
        textToSpeechHelper.speak(text)
    }

    actual fun stop() {
        textToSpeechHelper.stop()
    }
}
