package com.littleapp.stockmarket.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.launchActivity(
    finish: Boolean = false, block: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java).apply(block)
    startActivity(intent)
    if (finish && this is Activity) finish()
}