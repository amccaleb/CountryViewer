package com.nothesearentajaysapps.countryviewer

import com.nothesearentajaysapps.countryviewer.models.CountryModel
import com.nothesearentajaysapps.countryviewer.networking.IPeymanoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository to fetch countries from. This is a pile of anything fetched from the network, stored
 * locally, etc.
 */
class CountriesRepository {

    val countries: ArrayList<CountryModel> = ArrayList()

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
                    // TODO: Handle error case
                } else { // FIXME: This can break the "order presented in JSON" part of the test.
                    // Add anything new from the network response
                    for (country in responseBody) {
                        if (!countries.contains(country)) {
                            countries.add(country)
                        }
                    }
                }

                var x = 0
                // TODO: Pass the countries as a LiveData upward for the UI to refresh?

            }

            override fun onFailure(call: Call<List<CountryModel>>, throwable: Throwable) {
                // TODO: Handle failure case
            }
        })
    }
}