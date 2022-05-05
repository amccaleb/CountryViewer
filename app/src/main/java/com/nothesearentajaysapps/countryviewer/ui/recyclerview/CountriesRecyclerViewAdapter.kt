package com.nothesearentajaysapps.countryviewer.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nothesearentajaysapps.countryviewer.R
import com.nothesearentajaysapps.countryviewer.models.CountryModel

/**
 * Partially based on: https://github.com/velmurugan-murugesan/Android-Example/blob/master/RetrofitWithRecyclerviewKotlin/app/src/main/java/app/com/retrofitwithrecyclerviewkotlin/RecyclerAdapter.kt
 */
class CountriesRecyclerViewAdapter : RecyclerView.Adapter<CountriesRecyclerViewAdapter.CountriesViewHolder>() {

    private var countriesList: List<CountryModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row, parent, false)
        return CountriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countriesList[position]

        /**
         * Only show fields for data we have. For example, this case is missing a region and capital:
         * {
         *   "capital": "",
         *   "code": "HM",
         *   "currency": {
         *     "code": "AUD",
         *     "name": "Australian dollar",
         *     "symbol": "$"
         *   },
         *   "flag": "https://restcountries.eu/data/hmd.svg",
         *   "language": {
         *     "code": "en",
         *     "name": "English"
         *   },
         *   "name": "Heard Island and McDonald Islands",
         *   "region": ""
         * },
         */

        // Handle name and region
        var nameAndRegionText = country.name
        val region = country.region
        if (region.isNotBlank()) {
            nameAndRegionText += ", $region"
        }
        holder.nameAndRegion.text = nameAndRegionText

        // Handle the country code
        holder.code.text = country.code

        // Handle the capital of the country
        val capital = country.capital
        val capitalView = holder.capital
        if (capital.isBlank()) {
            capitalView.visibility = View.GONE
        } else {
            capitalView.visibility = View.VISIBLE
            capitalView.text = country.capital
        }
    }

    fun setCountries(countriesList: List<CountryModel>) {
        this.countriesList = countriesList

        // Flag that it's all changed without using `notifyDataSetChanged()` as Lint frowns on it.
        notifyItemRangeChanged(0, countriesList.size)
    }

    class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameAndRegion: TextView = itemView.findViewById(R.id.tvNameAndRegion)
        val code: TextView = itemView.findViewById(R.id.tvCode)
        val capital: TextView = itemView.findViewById(R.id.tvCapital)

    }
}