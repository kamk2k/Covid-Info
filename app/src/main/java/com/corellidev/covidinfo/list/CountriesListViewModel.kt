package com.corellidev.covidinfo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.corellidev.covidinfo.model.CountriesListItem

class CountriesListViewModel : ViewModel() {

    private val countries: MutableLiveData<List<CountriesListItem>> = MutableLiveData(listOf(
        CountriesListItem("Poland"), CountriesListItem("Italy"),
        CountriesListItem("China"), CountriesListItem("Spain")))

    fun getCountries() : LiveData<List<CountriesListItem>> = countries
}