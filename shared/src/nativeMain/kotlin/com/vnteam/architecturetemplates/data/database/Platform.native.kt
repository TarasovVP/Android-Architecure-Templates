package com.vnteam.architecturetemplates.data.database

import platform.Foundation.NSUUID

actual fun generateUUID(): String = NSUUID().UUIDString()