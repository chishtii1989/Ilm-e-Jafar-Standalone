package com.zakootaapps.ilmjafar

import android.util.Log
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

object GlobalExceptionHandler : Thread.UncaughtExceptionHandler {
    private var defaultHandler: Thread.UncaughtExceptionHandler? = null

    fun initialize() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        val stackTrace = sw.toString()
        Log.e("CRITICAL_CRASH", stackTrace)
        
        // Also write to a file that we can read from the agent
        try {
            val file = File("/data/local/tmp/crash_log.txt") // Not accessible. Let's write to app's files dir
            // Wait, we can't easily write to a known global path without context.
        } catch (ex: Exception) {}

        defaultHandler?.uncaughtException(t, e)
    }
}
