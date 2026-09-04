package com.zakootaapps.ilmjafar.engine.jafr.interfaces

import com.zakootaapps.ilmjafar.data.local.western_astrology.ZodiacSignEntity
import com.zakootaapps.ilmjafar.data.local.western_astrology.PlanetEntity

interface WesternAstrologyProvider {
    fun getZodiacSignByDate(month: Int, day: Int): ZodiacSignEntity?
    fun getPlanetByName(name: String): PlanetEntity?
}
