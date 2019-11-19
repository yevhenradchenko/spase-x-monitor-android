package com.spacexmonitor

data class Launch(
    val mission_name: String,
    val launch_year: Int,
    val rocket: Rocket,
    val details: String?,
    val links: Links
)

data class Rocket(
    val rocket_name: String,
    val rocket_type: String
)

data class Links(
    val mission_patch: String,
    val flickr_images: List<String>
)