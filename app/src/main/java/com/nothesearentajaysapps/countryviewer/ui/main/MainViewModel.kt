package com.nothesearentajaysapps.countryviewer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nothesearentajaysapps.countryviewer.CountriesRepository
import com.nothesearentajaysapps.countryviewer.models.CountryModel

class MainViewModel : ViewModel() {

    // Create a LiveData to maintain the proper state of the list of countries
    // Based on: https://developer.android.com/topic/libraries/architecture/livedata
    val countriesLiveData: MutableLiveData<List<CountryModel>> by lazy {
        MutableLiveData<List<CountryModel>>()
    }
    val repository: CountriesRepository = CountriesRepository(this)

    fun getCountries() {
        repository.appendCountriesFromNetwork()
    }
}