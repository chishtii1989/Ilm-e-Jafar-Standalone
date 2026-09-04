package com.zakootaapps.ilmjafar.domain.adad.engine.dob

import android.content.Context
import kotlinx.serialization.json.Json
import java.io.IOException

object NumerologyJsonLoader {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadProfile(context: Context, birthNumber: Int): DobNumerologyResult {
        return try {
            val fileName = "numerology/birth_$birthNumber.json"
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            json.decodeFromString<DobNumerologyResult>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            // Return a fallback or throw exception, but since this is local asset, it should be there.
            DobNumerologyResult(birthNumber = birthNumber, title = "Unknown")
        }
    }
}
