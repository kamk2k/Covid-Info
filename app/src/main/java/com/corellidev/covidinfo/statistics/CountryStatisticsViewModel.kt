package com.corellidev.covidinfo.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryStatisticsViewModel(
    private val getCountryStatisticsUseCase: GetCountryStatisticsUseCase,
    private val mapper: Mapper<CountryEntity, CountryStatistics>
) : ViewModel() {

    private val countryStatistics: MutableLiveData<CountryStatistics> = MutableLiveData()

    fun getCountryStatistics(): LiveData<CountryStatistics> = countryStatistics

    fun loadCountryStatistics(countryName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                countryStatistics.postValue(
                    mapper.map(
                        getCountryStatisticsUseCase.execute(
                            CountryEntity(countryName)
                        )
                    )
                )
            }
        }
    }
}