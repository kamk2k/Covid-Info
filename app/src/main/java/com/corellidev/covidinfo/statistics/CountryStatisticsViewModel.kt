package com.corellidev.covidinfo.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corellidev.covidinfo.mapper.CountryEntityToCountryStatistics
import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import kotlinx.coroutines.launch

class CountryStatisticsViewModel(
    private val getCountryStatisticsUseCase: GetCountryStatisticsUseCase,
    private val countryEntityToCountryStatistics: CountryEntityToCountryStatistics
) : ViewModel() {

    private val countryStatistics: MutableLiveData<CountryStatistics> = MutableLiveData()

    fun getCountryStatistics(): LiveData<CountryStatistics> = countryStatistics

    fun loadCountryStatistics(countryName: String) {
        viewModelScope.launch {
            countryStatistics.postValue(
                countryEntityToCountryStatistics.map(
                    getCountryStatisticsUseCase.execute(
                        CountryEntity(countryName)
                    )
                )
            )
        }
    }
}