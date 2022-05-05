package com.nothesearentajaysapps.countryviewer.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nothesearentajaysapps.countryviewer.CountriesRepository
import com.nothesearentajaysapps.countryviewer.models.CountryModel

class MainViewModel : ViewModel() {

    companion object {
        const val LOG_TAG = "MainViewModel"
    }

    // Create a LiveData to maintain the proper state of the list of countries
    // Based on: https://developer.android.com/topic/libraries/architecture/livedata
    val countriesLiveData: MutableLiveData<List<CountryModel>> by lazy {
        MutableLiveData<List<CountryModel>>()
    }
    private val repository: CountriesRepository = CountriesRepository(this)

    fun getCountries() {
        // Note: If the results from the network we're constantly changing, this caching scheme
        // would be a bad assumption.
        if (repository.countries.isEmpty()) {
            Log.d(LOG_TAG, "Fetching countries from network")
            repository.appendCountriesFromNetwork()
        } else {
            Log.d(LOG_TAG, "Loading cached countries")
            repository.getCachedCountries()
        }
    }
}