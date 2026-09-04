package com.zakootaapps.ilmjafar.util

import android.util.Log
import com.zakootaapps.ilmjafar.BuildConfig

object ActivationDebug {
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, throwable)
        }
    }
    fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }
}
