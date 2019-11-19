package com.spacexmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.launch_row.view.*

class MainAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    private val data = mutableListOf<Launch>()

    fun setData(data: List<Launch>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.launch_row, parent, false)
        return CustomViewHolder(cellForRow, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val launch = data[position]
        holder.bind(launch)
    }

    fun getItemByPosition(position: Int) = data[position]

    class CustomViewHolder(
        view: View,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener(object :View.OnClickListener {
                override fun onClick(p0: View?) {
                    onItemClickListener.onItemClicked(adapterPosition)
                }
            })
        }

        fun bind(launch: Launch) {
            with(itemView) {
                launchMissionTitle.text = "Mission: ${launch.mission_name}"
                launchYear.text = "Launch year: ${launch.launch_year}"
                launchRocketName.text =
                    "Rocket: ${launch.rocket.rocket_name} (${launch.rocket.rocket_type})"

                if (launch.details == null) {
                    launchDetails.isVisible = false
                } else {
                    launchDetails.text = "Details: \n${launch.details}"
                    launchDetails.isVisible = true
                }

                if (launch.links.mission_patch == null) {
                    launchLogo.setImageResource(R.drawable.space_x_logo)
                } else {
                    Picasso.get().load(launch.links.mission_patch).into(launchLogo)
                }

                if (launch.links.flickr_images.isNullOrEmpty()) {
                    launchImage.setImageResource(R.drawable.space_x_logo)
                } else {
                    Picasso.get().load(launch.links.flickr_images[0]).into(launchImage)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}

