package com.corellidev.covidinfo.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseRecyclerViewAdapter<T>(data: LiveData<List<T>>,
                                          owner: LifecycleOwner,
                                          @LayoutRes protected val layout: Int): RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemHolder<T>>() {

    private val items = mutableListOf<T>()

    init {
        data.observe(owner, Observer {
            owner.lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    items.clear()
                    items.addAll(it)
                    notifyDataSetChanged()
                }
            }
        })
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<T>

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    protected fun inflateView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    abstract class ItemHolder<T>(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }
}