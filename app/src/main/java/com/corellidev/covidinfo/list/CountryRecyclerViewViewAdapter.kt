package com.corellidev.covidinfo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.corellidev.covidinfo.R
import com.corellidev.covidinfo.common.BaseRecyclerViewAdapter
import com.corellidev.covidinfo.model.CountriesListItem
import kotlinx.android.synthetic.main.list_item_countries.view.*

class CountryRecyclerViewViewAdapter(
    values: LiveData<List<CountriesListItem>>,
    owner: LifecycleOwner,
    val onItemClick: (countryName: String) -> Unit)
    : BaseRecyclerViewAdapter<CountriesListItem>(values, owner, R.layout.list_item_countries) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_countries, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : ItemHolder<CountriesListItem>(view) {
        private val contentView: TextView = view.country_item_content
        private val container: LinearLayout = view.country_item

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        override fun bind(item: CountriesListItem) {
            contentView.text = item.countryName
            container.setOnClickListener{ onItemClick(item.countryName) }
        }
    }
}