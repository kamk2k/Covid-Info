package com.corellidev.data.util

import org.joda.time.Duration

class TimeProvider {

    fun getCurrentTime() : Long = System.currentTimeMillis()

    fun hasXMinutesPassedSince(start: Long, end: Long, minutes: Int): Boolean =
        Duration(start, end).standardMinutes >= minutes

}