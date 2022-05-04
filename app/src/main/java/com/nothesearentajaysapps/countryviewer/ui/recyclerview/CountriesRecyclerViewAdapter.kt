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

        holder.nameAndRegion.text = "${country.name}, ${country.region}"
        holder.code.text = country.code
        holder.capital.text = country.capital
    }

    fun setCountries(countriesList: List<CountryModel>) {
        this.countriesList = countriesList
        notifyDataSetChanged()
    }

    class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameAndRegion: TextView = itemView.findViewById(R.id.tvNameAndRegion)
        val code: TextView = itemView.findViewById(R.id.tvCode)
        val capital: TextView = itemView.findViewById(R.id.tvCapital)

    }
}