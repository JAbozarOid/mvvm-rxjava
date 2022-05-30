package com.ryanair.androidchallenge.ui.fragments.airport.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ryanair.androidchallenge.data.airports.network.model.AirportResponse
import com.ryanair.androidchallenge.databinding.AirportViewItemBinding


class AirportRecAdapter :
    ListAdapter<AirportResponse, AirportRecAdapter.ViewHolder>(AirportDiffUtilsListAdapter) {

    private val TAG = "AirportRecAdapter"

    object AirportDiffUtilsListAdapter : DiffUtil.ItemCallback<AirportResponse>() {
        override fun areItemsTheSame(
            oldItem: AirportResponse,
            newItem: AirportResponse
        ): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(
            oldItem: AirportResponse,
            newItem: AirportResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.size > 4) 5 else currentList.size
    }

    class ViewHolder(val binding: AirportViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(airportResponse: AirportResponse) {
            binding.airportModel = airportResponse

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AirportViewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (currentList.isEmpty()) {
            return
        }
        try {
            currentList.get(position)?.let { item ->

                holder.bind(item)
            }
        } catch (e: Exception) {
            Log.e(TAG, "onBindViewHolder: ", e)
        }
    }

    fun setList(newData: List<AirportResponse>) {
        try {
            submitList(newData)
        } catch (e: Exception) {
            Log.e(TAG, "setList: ", e)
        }
    }


}

