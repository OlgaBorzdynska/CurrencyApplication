package com.example.currencyapplication.data

data class DateCurrencyDto(
    val base: String,
    val date: String,
    val historical: Boolean,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)

sealed class CurrencyRow(val name: String, val rate: Double?, val date: String) {

    class SingleRate(name: String, rate: Double, date: String) : CurrencyRow(name, rate, date)

    class DateHeader(date: String, name: String = "Dzień: ") : CurrencyRow(name, null, date)
}



fun DateCurrencyDto.toDateRatesList() : ArrayList<CurrencyRow>{
    val ratesList = ArrayList<CurrencyRow>()
    ratesList.add(CurrencyRow.DateHeader(date))
    ratesList.add(CurrencyRow.SingleRate("AUD:", rates.AUD, date))
    ratesList.add(CurrencyRow.SingleRate("BGN:", rates.BGN, date))
    ratesList.add(CurrencyRow.SingleRate("BRL:", rates.BRL, date))
    ratesList.add(CurrencyRow.SingleRate("BYN:", rates.BYN, date))
    ratesList.add(CurrencyRow.SingleRate("CAD:", rates.CAD, date))
    ratesList.add(CurrencyRow.SingleRate("CHF:", rates.CHF, date))
    ratesList.add(CurrencyRow.SingleRate("CNY:", rates.CNY, date))
    ratesList.add(CurrencyRow.SingleRate("CZK:", rates.CZK, date))
    ratesList.add(CurrencyRow.SingleRate("DKK:", rates.DKK, date))
    ratesList.add(CurrencyRow.SingleRate("GBP:", rates.GBP, date))
    ratesList.add(CurrencyRow.SingleRate("HKD:", rates.HKD, date))
    ratesList.add(CurrencyRow.SingleRate("HRK:", rates.HRK, date))
    ratesList.add(CurrencyRow.SingleRate("HUF:", rates.HUF, date))
    ratesList.add(CurrencyRow.SingleRate("IDR:", rates.IDR, date))
    ratesList.add(CurrencyRow.SingleRate("ILS:", rates.ILS, date))
    ratesList.add(CurrencyRow.SingleRate("JPY:", rates.JPY, date))
    ratesList.add(CurrencyRow.SingleRate("KRW:", rates.KRW, date))
    ratesList.add(CurrencyRow.SingleRate("LRD:", rates.LRD, date))
    ratesList.add(CurrencyRow.SingleRate("MOP:", rates.MOP, date))
    ratesList.add(CurrencyRow.SingleRate("MRO:", rates.MRO, date))
    ratesList.add(CurrencyRow.SingleRate("NOK:", rates.NOK, date))
    ratesList.add(CurrencyRow.SingleRate("NPR:", rates.NPR, date))
    ratesList.add(CurrencyRow.SingleRate("PLN:", rates.PLN, date))
    ratesList.add(CurrencyRow.SingleRate("RUB:", rates.RUB, date))
    ratesList.add(CurrencyRow.SingleRate("UAH:", rates.UAH, date))
    ratesList.add(CurrencyRow.SingleRate("USD:", rates.USD, date))
    ratesList.add(CurrencyRow.SingleRate("ZWL:", rates.ZWL, date))

    return  ratesList
}