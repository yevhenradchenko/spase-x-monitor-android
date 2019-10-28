package com.spacexmonitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_mission_list.*
import okhttp3.*
import java.io.IOException

class MissionListFragment : Fragment() {

    companion object {
        private val MISSION_LIST = "mission list"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mission_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launchesRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchJson()
    }

    fun fetchJson() {
        println("Attempt fetch Json")

        val url = "https://api.spacexdata.com/v3/launches"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val listType = object : TypeToken<List<Launches>>() {}.type
                val spaceXFeed = gson.fromJson<List<Launches>>(body, listType)

                activity?.runOnUiThread {
                    launchesRecyclerView.adapter = MainAdapter(SpaceXFeed(spaceXFeed))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }
}

class SpaceXFeed(val launches: List<Launches>)

class Launches(
    val mission_name: String,
    val launch_year: Int,
    val rocket: Rocket,
    val details: String?,
    val links: Links
)

class Rocket(
    val rocket_name: String,
    val rocket_type: String
)

class Links(
    val mission_patch: String,
    val flickr_images: List<String>
)