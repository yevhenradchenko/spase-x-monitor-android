package com.spacexmonitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MissionChartFragment : Fragment() {

    companion object {
        private val MISSION_CHART = "mission chart"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mission_chart, container, false)
    }
}
