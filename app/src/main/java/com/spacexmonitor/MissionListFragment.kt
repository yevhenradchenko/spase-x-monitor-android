package com.spacexmonitor

import android.os.Bundle
import android.util.Log
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

class MissionListFragment : Fragment(), MainAdapter.OnItemClickListener {

    private val mainAdapter = MainAdapter(this)
    override fun onItemClicked(position: Int) {
        val launch = mainAdapter.getItemByPosition(position)
        Log.d("DATA",launch.toString())
    }

    companion object {
        private val MISSION_LIST = "mission list"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mission_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchJson()
        launchesRecyclerView.layoutManager = LinearLayoutManager(view.context)
        launchesRecyclerView.adapter = mainAdapter

    }

    private fun fetchJson() {
        println("Attempt fetch Json....")

        val url = "https://api.spacexdata.com/v3/launches"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val listType = object : TypeToken<List<Launch>>() {}.type
                val spaceXFeed = gson.fromJson<List<Launch>>(body, listType)

                activity?.runOnUiThread {
                    mainAdapter.setData(spaceXFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }
}