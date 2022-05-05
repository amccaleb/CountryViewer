package com.nothesearentajaysapps.countryviewer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nothesearentajaysapps.countryviewer.R
import com.nothesearentajaysapps.countryviewer.models.CountryModel
import com.nothesearentajaysapps.countryviewer.ui.recyclerview.CountriesRecyclerViewAdapter

/**
 * Partially based on:
 * - https://github.com/velmurugan-murugesan/Android-Example/blob/master/RetrofitWithRecyclerviewKotlin/app/src/main/java/app/com/retrofitwithrecyclerviewkotlin/MainActivity.kt
 * - https://stackoverflow.com/questions/27809524/recyclerview-not-call-oncreateviewholder
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: CountriesRecyclerViewAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate our fragment
        val fragment = inflater.inflate(R.layout.main_fragment, container, false)

        // Set up our RecyclerView
        recyclerView = fragment.findViewById(R.id.rvCountries)
        recyclerViewAdapter = CountriesRecyclerViewAdapter()

        // Ensure a LayoutManager is set up so the RecyclerView knows how to lay out the content.
        recyclerView.layoutManager = LinearLayoutManager(fragment.context)
        recyclerView.adapter = recyclerViewAdapter

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up connections to the data to display
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Create the observer which updates the UI.
        val countriesObserver = Observer<List<CountryModel>> { newList ->
            // Update the RecyclerView Adapter to use data from the new list
            recyclerViewAdapter.setCountries(newList)
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.countriesLiveData.observe(viewLifecycleOwner, countriesObserver)

        // Fetch the countries list, which will post results to the above LiveData. Since this data
        // is held in a ViewModel, it already survives the Activity being recreated in a low
        // resource situation.
        viewModel.getCountries()
    }

}