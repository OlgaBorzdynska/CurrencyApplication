package com.example.currencyapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapplication.common.Resource
import com.example.currencyapplication.data.CurrencyRow
import com.example.currencyapplication.data.DateCurrencyDto
import com.example.currencyapplication.data.toDateRatesList
import com.example.currencyapplication.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CurrencyViewModel
@Inject
constructor(private val repository: CurrencyRepository) : ViewModel() {

    private var daysToLoad = 0;
    private var itemsLoaded = ArrayList<CurrencyRow>()
    private val _response = MutableLiveData<Resource<DateCurrencyDto>>()
    val currencyResponse: LiveData<Resource<DateCurrencyDto>>
        get() = _response

    init {
        getCurrencyForToday()
    }


    private fun getCurrencyForToday(){
        val today = LocalDate.now()
        getCurrencyByDate(today.toString())
    }

    fun getCurrencyPreviousDay(){
        daysToLoad++
        val previousDate = LocalDate.now().minusDays(daysToLoad.toLong())
        getCurrencyByDate(previousDate.toString())
    }

    fun getItemsLoadedForAdapter() : ArrayList<CurrencyRow> {
        return itemsLoaded
    }

    private fun getCurrencyByDate(date: String) = viewModelScope.launch {
        val result = repository.getCurrencyByDate(date)
        _response.postValue(result)

        if(result is Resource.Success && result.data != null){
            itemsLoaded.addAll(result.data.toDateRatesList())
        }
    }
}