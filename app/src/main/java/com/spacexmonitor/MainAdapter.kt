package com.spacexmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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
        val launch = spaceXFeed.launches[position]
        holder.view.launchMissionTitle.text = "Mission: ${launch.mission_name}"
        holder.view.launchYear.text = "Launch year: ${launch.launch_year}"
        holder.view.launchRocketName.text =
            "Rocket: ${launch.rocket.rocket_name} (${launch.rocket.rocket_type})"
        if (launch.details == null) {
            holder.view.launchDetails.isVisible = false
        } else {
            holder.view.launchDetails.text = "Details: \n${launch.details}"
            holder.view.launchDetails.isVisible = true

        }

        if (launch.links.mission_patch == null) {
            holder.view.launchLogo.setImageResource(R.drawable.space_x_logo)
        } else {
            Picasso.get().load(launch.links.mission_patch).into(holder.view.launchLogo)
        }
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}