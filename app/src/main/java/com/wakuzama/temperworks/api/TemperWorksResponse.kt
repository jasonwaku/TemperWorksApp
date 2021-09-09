package com.wakuzama.temperworks.api

import com.wakuzama.temperworks.data.TemperWorksItem

data class TemperWorksResponse(
    val data: List<TemperWorksItem>
)