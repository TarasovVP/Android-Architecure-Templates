package com.vnteam.architecturetemplates.data.database

actual fun generateUUID(): String = js("crypto.randomUUID().toString()").unsafeCast<String>()