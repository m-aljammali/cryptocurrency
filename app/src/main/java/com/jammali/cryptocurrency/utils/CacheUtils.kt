package com.jammali.cryptocurrency.utils

import android.util.Log
import java.util.Calendar

object CacheUtils {


    fun getCurrentTime() : String {
        return Calendar.getInstance().getTime().time.toString()
    }


    fun hasCacheExp (savedTime: String) : Boolean{
        val currentTime = getCurrentTime().toLong()
        val timeDifference = currentTime - savedTime.toLong()
        return timeDifference >= 24 * 60 * 60 * 1000
    }

}