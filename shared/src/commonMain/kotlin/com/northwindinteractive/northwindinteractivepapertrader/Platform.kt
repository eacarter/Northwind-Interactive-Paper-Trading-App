package com.northwindinteractive.northwindinteractivepapertrader

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform