package com.littleapp.stockmarket.presentation.data.remote

import com.littleapp.stockmarket.Unit.DATA
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = DATA.API_KEY_STOCK_MARKET
    ): ResponseBody
}