package com.corellidev.covidinfo.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.corellidev.covidinfo.R

class CountryStatisticsFragment : Fragment() {

    companion object {
        fun newInstance() =
            CountryStatisticsFragment()
    }

    private lateinit var viewModel: CountryStatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_statistics, container, false)
        view.findViewById<TextView>(R.id.hello_text).setOnClickListener {
            val action = CountryStatisticsFragmentDirections.actionCountryStatisticsFragmentToCountriesFragment()
            it.findNavController().navigate(action)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryStatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}