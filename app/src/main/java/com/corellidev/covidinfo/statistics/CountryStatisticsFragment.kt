package com.corellidev.covidinfo.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.corellidev.covidinfo.R
import kotlinx.android.synthetic.main.fragment_country_statistics.*
import java.text.DateFormat


class CountryStatisticsFragment : Fragment() {

    private lateinit var viewModel: CountryStatisticsViewModel
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_country_statistics, container, false)
        rootView.findViewById<TextView>(R.id.confirmed_text).setOnClickListener {
            val action = CountryStatisticsFragmentDirections.actionCountryStatisticsFragmentToCountriesFragment()
            it.findNavController().navigate(action)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryStatisticsViewModel::class.java)
        viewModel.getCountry().observe(this) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = it
        }
        viewModel.getDate().observe(this) {
            date_text.text = DateFormat.getDateInstance().format(it)
        }
        viewModel.getConfirmed().observe(this) {
            confirmed_value.text = it.toString()
        }
        viewModel.getRecovered().observe(this) {
            recovered_value.text = it.toString()
        }
        viewModel.getDeaths().observe(this) {
            deaths_value.text = it.toString()
        }
    }


}