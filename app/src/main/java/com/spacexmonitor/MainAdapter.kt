package com.spacexmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.launch_row.view.*

class MainAdapter(val spaceXFeed: SpaceXFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return spaceXFeed.launches.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.launch_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val launchTitle = spaceXFeed.launches[position].toString()
        holder.view.launchesTextTitle.text = launchTitle
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}