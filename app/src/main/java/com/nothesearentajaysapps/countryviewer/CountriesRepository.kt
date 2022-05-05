package com.nothesearentajaysapps.countryviewer

import android.util.Log
import com.nothesearentajaysapps.countryviewer.models.CountryModel
import com.nothesearentajaysapps.countryviewer.networking.IPeymanoAPI
import com.nothesearentajaysapps.countryviewer.ui.main.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository to fetch countries from. This is a pile of anything fetched from the network, stored
 * locally, etc.
 */
class CountriesRepository(val viewModel: MainViewModel) {

    companion object {
        const val LOG_TAG = "CountriesRepository"
    }

    val countries: ArrayList<CountryModel> = ArrayList()

    /**
     * Requests a set of countries from the network and appends any new ones to our list.
     * Upon success, updates a LiveData with the results.
     */
    fun appendCountriesFromNetwork() {
        // Make the call to the endpoint via Retrofit
        // Based on: https://github.com/velmurugan-murugesan/Android-Example/blob/master/RetrofitWithRecyclerviewKotlin/app/src/main/java/app/com/retrofitwithrecyclerviewkotlin/MainActivity.kt
        val countriesCall = IPeymanoAPI.create().getCountries()

        countriesCall.enqueue( object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                val responseBody = response.body()
                if (responseBody == null) {
                    // Well that was unexpected.
                    Log.e(LOG_TAG, "Got an error from the network: " + response.errorBody().toString())
                } else { // FIXME: This can break the "order presented in JSON" part of the test, but does handle ensuring no dupes show up when relaunching the Activity.
                    // Add anything new from the network response
                    for (country in responseBody) {
                        if (!countries.contains(country)) {
                            countries.add(country)
                        }
                    }
                }

                // Pass the countries as a LiveData upward for the UI to refresh
                viewModel.countriesLiveData.value = countries
            }

            override fun onFailure(call: Call<List<CountryModel>>, throwable: Throwable) {
                // TODO: Handle failure case
            }
        })
    }
}