package com.zakootaapps.ilmjafar.engine.jafr.modules.tashkhees

import com.zakootaapps.ilmjafar.engine.jafr.core.RohaniTashkheesResult
import com.zakootaapps.ilmjafar.engine.jafr.interfaces.AbjadProvider

class TashkheesCalculator(private val abjadProvider: AbjadProvider) {

    fun calculate(name: String): RohaniTashkheesResult {
        val elements = mutableMapOf("Fire" to 0, "Water" to 0, "Earth" to 0, "Air" to 0)
        
        for (char in name.replace(" ", "")) {
            val properties = abjadProvider.getLetterProperties(char.toString())
            if (properties != null) {
                when {
                    properties.element.contains("آگ") || properties.element.contains("Fire") -> elements["Fire"] = (elements["Fire"] ?: 0) + 1
                    properties.element.contains("پانی") || properties.element.contains("Water") -> elements["Water"] = (elements["Water"] ?: 0) + 1
                    properties.element.contains("مٹی") || properties.element.contains("Earth") -> elements["Earth"] = (elements["Earth"] ?: 0) + 1
                    properties.element.contains("ہوا") || properties.element.contains("Air") -> elements["Air"] = (elements["Air"] ?: 0) + 1
                }
            }
        }
        
        val dominantElement = elements.maxByOrNull { it.value }?.key ?: "Unknown"
        val diagnosis = "The dominant element in your name is $dominantElement, which indicates your spiritual orientation."
        
        return RohaniTashkheesResult(
            elements = elements,
            dominantElement = dominantElement,
            diagnosis = diagnosis
        )
    }
}
