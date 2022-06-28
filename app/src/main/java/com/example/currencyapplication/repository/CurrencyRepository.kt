package com.example.currencyapplication.repository

import com.example.currencyapplication.api.FixerApi
import com.example.currencyapplication.common.Resource
import com.example.currencyapplication.data.DateCurrencyDto
import retrofit2.Response
import javax.inject.Inject



class CurrencyRepository
@Inject constructor(private val api: FixerApi) {

    suspend fun getCurrencyByDate(date : String): Resource<DateCurrencyDto> {

        return try {
            Resource.Success(
                data = api.getCurrencyByDate(date)
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}