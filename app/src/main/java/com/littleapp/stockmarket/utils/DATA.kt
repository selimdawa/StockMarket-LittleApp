@file:Suppress("SpellCheckingInspection")

package com.littleapp.stockmarket.utils

object DATA {

    const val STOCK_MARKET = "Stock Market"

    //Stock Market
    const val API_KEY_STOCK = "CY75KFHR7APO4MSF"
    const val BASE_URL_STOCK = "https://alphavantage.co/"

    // Formats
    fun symbolBrackets(s: String) = "($s)"

    // Theme Attributes
    const val COLOR_ERROR = "colorError"
    const val COLOR_ON_BACKGROUND = "colorOnBackground"
    const val MC_TRACK = "mc_track"
    const val MC_TICK = "mc_tick"
    const val MC_BG = "mc_bg"
}