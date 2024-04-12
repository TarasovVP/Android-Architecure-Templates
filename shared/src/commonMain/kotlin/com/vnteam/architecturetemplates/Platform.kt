package com.vnteam.architecturetemplates

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform