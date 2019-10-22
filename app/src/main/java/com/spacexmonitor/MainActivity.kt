package com.spacexmonitor

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
        launchesRecyclerView.adapter = MainAdapter()
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

                val listType = object : TypeToken<List<SpaceXFeed>>() {}.type
                val spaceXFeed = gson.fromJson<List<SpaceXFeed>>(body, listType)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }
}

class SpaceXFeed(val images: List<Images>)

class Images(val flight_number: Int, val name: String)