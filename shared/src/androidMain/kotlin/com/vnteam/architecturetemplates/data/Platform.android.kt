package com.vnteam.architecturetemplates.data

import config.BuildConfig
import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()

actual fun baseUrl(): String = "http://${BuildConfig.LOCAL_IP}:8080/"