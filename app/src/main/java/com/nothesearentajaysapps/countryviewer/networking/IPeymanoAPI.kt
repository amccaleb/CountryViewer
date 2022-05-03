package com.nothesearentajaysapps.countryviewer.networking

import com.nothesearentajaysapps.countryviewer.models.CountryModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Based on: https://github.com/velmurugan-murugesan/Android-Example/blob/master/RetrofitWithRecyclerviewKotlin/app/src/main/java/app/com/retrofitwithrecyclerviewkotlin/ApiInterface.kt
interface IPeymanoAPI {

    @GET("countries.json")
    fun getCountries() : Call<List<CountryModel>>

    companion object {

        var BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

        fun create() : IPeymanoAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IPeymanoAPI::class.java)

        }
    }
}