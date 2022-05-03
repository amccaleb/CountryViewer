package com.nothesearentajaysapps.countryviewer.ui.main

import androidx.lifecycle.ViewModel
import com.nothesearentajaysapps.countryviewer.CountriesRepository

class MainViewModel : ViewModel() {

    val repository: CountriesRepository = CountriesRepository()

    fun getCountries() {
        repository.appendCountriesFromNetwork()
    }
}