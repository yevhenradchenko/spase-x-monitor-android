package com.spacexmonitor

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.fragment_mission_chart.*

class MissionChartFragment : Fragment() {
    companion object {
        private val MISSION_CHART = "mission chart"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val firstChartEntity = ChartEntity(Color.WHITE, graph1)
        val secondChartEntity = ChartEntity(Color.YELLOW, graph2)

        val list = ArrayList<ChartEntity>()
        list.add(firstChartEntity)
        list.add(secondChartEntity)
        graph?.legendArray = legendArr
        graph?.setList(list)

        return inflater.inflate(R.layout.fragment_mission_chart, container, false)
    }

    private val graph1 = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
    private val graph2 = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f)
    private val legendArr = arrayOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")
}

