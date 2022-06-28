package com.example.currencyapplication.api


import com.example.currencyapplication.common.Constants.API_KEY
import com.example.currencyapplication.data.DateCurrencyDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FixerApi {

    @GET("/fixer/{date}?apikey=$API_KEY")
    suspend fun getCurrencyByDate(@Path("date") date : String) : DateCurrencyDto
}