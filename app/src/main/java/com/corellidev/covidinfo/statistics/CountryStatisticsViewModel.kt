package com.corellidev.covidinfo.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CountryStatisticsViewModel : ViewModel() {

    private val country: MutableLiveData<String> = MutableLiveData("Poland")
    private val date: MutableLiveData<Date> = MutableLiveData(Date())
    private val confirmed: MutableLiveData<Int> = MutableLiveData(12)
    private val recovered: MutableLiveData<Int> = MutableLiveData(12)
    private val deaths: MutableLiveData<Int> = MutableLiveData(12)

    fun getCountry() : LiveData<String> = country
    fun getDate() : LiveData<Date> = date
    fun getConfirmed() : LiveData<Int> = confirmed
    fun getRecovered() : LiveData<Int> = recovered
    fun getDeaths() : LiveData<Int> = deaths

}