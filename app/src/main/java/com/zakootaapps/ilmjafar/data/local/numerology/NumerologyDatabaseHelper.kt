package com.zakootaapps.ilmjafar.data.local.numerology

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class NumerologyDatabaseHelper(private val context: Context) : 
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "numerology.db"
        private const val DATABASE_VERSION = 3
    }

    private val dbPath = context.getDatabasePath(DATABASE_NAME).absolutePath

    init {
        copyDatabaseIfNeeded()
    }

    private fun copyDatabaseIfNeeded() {
        val prefs = com.zakootaapps.ilmjafar.util.KeystoreManager.getEncryptedPrefs(context)
        val currentVersion = prefs.getInt("${DATABASE_NAME}_version", 0)
        val dbFile = File(dbPath)
        if (!dbFile.exists() || currentVersion < DATABASE_VERSION) {
            dbFile.parentFile?.mkdirs()
            if (dbFile.exists()) {
                dbFile.delete()
            }
            try {
                context.assets.open("numerology_db/numerology.db").use { input ->
                    FileOutputStream(dbFile).use { output ->
                        input.copyTo(output)
                    }
                }
                prefs.edit().putInt("${DATABASE_NAME}_version", DATABASE_VERSION).apply()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Handled by copying pre-populated database
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handled in init
    }
}
