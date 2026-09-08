package com.littleapp.stockmarket

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.selimdawa.multicolors.MultiColorManager

@HiltAndroidApp
class HiltApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MultiColorManager.init(this)
    }
}