package com.zakootaapps.ilmjafar.engine.jafr.interfaces

import com.zakootaapps.ilmjafar.data.local.modules.asmaulhusna.AsmaUlHusnaEntity

interface AsmaUlHusnaProvider {
    fun getAsmaUlHusnaByName(name: String): AsmaUlHusnaEntity?
    fun getAsmaUlHusnaByAbjadKabeer(abjadValue: Int): List<AsmaUlHusnaEntity>
}
