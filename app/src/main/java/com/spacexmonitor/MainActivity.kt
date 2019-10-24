package com.spacexmonitor

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJson()

        launchesRecyclerView.layoutManager = LinearLayoutManager(this)
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

                runOnUiThread {
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
    val mission_patch: String
)

