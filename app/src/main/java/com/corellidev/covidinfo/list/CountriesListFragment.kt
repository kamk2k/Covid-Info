package com.corellidev.covidinfo.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corellidev.covidinfo.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesListFragment : Fragment() {

    private val viewModel: CountriesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_countries_list, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowHomeEnabled(false)
        }
        setHasOptionsMenu(true)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter =
                    CountryRecyclerViewViewAdapter(
                        viewModel.getCountries(),
                        this@CountriesListFragment
                    ) { countryName ->
                        Log.d("MyTag", "countryName = " + countryName)
                    }
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_actions, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}