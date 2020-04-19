package com.corellidev.covidinfo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corellidev.covidinfo.mapper.CountryEntityToCountriesListItem
import com.corellidev.covidinfo.model.CountriesListItem
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import kotlinx.coroutines.launch

class CountriesListViewModel(
    private val getSupportedCountriesUseCase: GetSupportedCountriesUseCase,
    private val countryEntityToCountriesListItem: CountryEntityToCountriesListItem
) : ViewModel() {

    private val countries: MutableLiveData<List<CountriesListItem>> = MutableLiveData()

    init {
        viewModelScope.launch {
            countries.postValue(
                countryEntityToCountriesListItem.map(getSupportedCountriesUseCase.execute())
            )
        }
    }

    fun getCountries(): LiveData<List<CountriesListItem>> = countries
}