package com.spacexmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.spacexmonitor.MissionChartFragment as MissionChartFragment
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val missionList: Fragment = MissionListFragment()
    private val chartView: Fragment = MissionChartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, missionList)
                .commit()
        }

        // This is my brilliant hack how to save parsed fragment state for RecyclerView
        // Please don't touch it
        supportFragmentManager.beginTransaction()
            .add(R.id.container, chartView)
            .hide(chartView)
            .commit()

        bottomNavigationBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.launchesMenuItem -> {
                    supportFragmentManager.beginTransaction()
                        .hide(chartView)
                        .show(missionList)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.chartsMenuItem -> {
                    supportFragmentManager.beginTransaction()
                        .hide(missionList)
                        .show(chartView)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}




