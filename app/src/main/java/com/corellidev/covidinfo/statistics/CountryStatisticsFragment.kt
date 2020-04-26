package com.corellidev.covidinfo.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.corellidev.covidinfo.R
import kotlinx.android.synthetic.main.fragment_country_statistics.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat


class CountryStatisticsFragment : Fragment() {

    private val viewModel: CountryStatisticsViewModel by viewModel()
    private lateinit var rootView: View
    private val args: CountryStatisticsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_country_statistics, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.let {actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCountryStatistics().observe(viewLifecycleOwner) { countryStatistics ->
            if(countryStatistics.noStats) {
                (activity as? AppCompatActivity)?.supportActionBar?.title = countryStatistics.countryName
                //TODO no stats layout
            } else {
                (activity as? AppCompatActivity)?.supportActionBar?.title = countryStatistics.countryName
                date_text.text = DateFormat.getDateInstance().format(countryStatistics.date!!)
                confirmed_value.text = countryStatistics.confirmed.toString()
                recovered_value.text = countryStatistics.recovered.toString()
                deaths_value.text = countryStatistics.deaths.toString()
            }
        }
        viewModel.loadCountryStatistics(args.countryName)
    }


}