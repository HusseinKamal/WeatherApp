package com.hussein.weatherapp.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation() : Location?
}