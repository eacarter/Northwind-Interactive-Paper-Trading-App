package com.northwindinteractive.northwindinteractivepapertrader

import androidx.compose.ui.window.ComposeUIViewController
import com.northwindinteractive.northwindinteractivepapertrader.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}