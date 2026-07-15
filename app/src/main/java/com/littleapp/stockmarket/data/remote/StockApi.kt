package com.littleapp.stockmarket.data.remote

import com.littleapp.stockmarket.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = Constants.API_KEY
    ): ResponseBody
}