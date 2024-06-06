package com.vnteam.architecturetemplates.data.database

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()