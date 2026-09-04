package com.zakootaapps.ilmjafar.engine.jafr.core

import com.zakootaapps.ilmjafar.engine.jafr.interfaces.*
import com.zakootaapps.ilmjafar.engine.jafr.modules.abjad.AbjadCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.adaad.AdaadCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.takseer.TakseerCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.transformation.TransformationCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.ism.IsmCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.ghalibmaghloob.GhalibMaghloobCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.compatibility.CompatibilityCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.tashkhees.TashkheesCalculator
import com.zakootaapps.ilmjafar.engine.jafr.modules.falnama.FalnamaCalculator

class JafrEngine(
    private val abjadProvider: AbjadProvider,
    private val numerologyProvider: NumerologyProvider,
    private val asmaUlHusnaProvider: AsmaUlHusnaProvider,
    private val westernAstrologyProvider: WesternAstrologyProvider
) {
    private val validationEngine = ValidationEngine()
    private val abjadCalculator = AbjadCalculator(abjadProvider)
    private val adaadCalculator = AdaadCalculator()
    private val takseerCalculator = TakseerCalculator()
    private val transformationCalculator = TransformationCalculator()
    private val ismCalculator = IsmCalculator(asmaUlHusnaProvider)
    private val ghalibMaghloobCalculator = GhalibMaghloobCalculator()
    private val compatibilityCalculator = CompatibilityCalculator(numerologyProvider)
    private val tashkheesCalculator = TashkheesCalculator(abjadProvider)
    private val falnamaCalculator = FalnamaCalculator(abjadProvider)

    fun process(input: JafrInput): JafrOutput {
        validationEngine.validate(input)

        val nameAbjadKabeer = abjadCalculator.calculateKabeer(input.name)
        val motherAbjadKabeer = abjadCalculator.calculateKabeer(input.motherName)
        
        val abjadSagheer = abjadCalculator.calculateSagheer(input.name)
        val abjadShamsi = abjadCalculator.calculateShamsi(input.name)
        val abjadQamari = abjadCalculator.calculateQamari(input.name)

        val adaadResult = adaadCalculator.calculate(input.dateOfBirth, nameAbjadKabeer)
        val takseerResult = takseerCalculator.calculate(input.name)
        val transformationResult = transformationCalculator.calculate(takseerResult.haroof)
        
        val ismResult = ismCalculator.calculate(nameAbjadKabeer)
        val ghalibMaghloobResult = ghalibMaghloobCalculator.calculate(nameAbjadKabeer, motherAbjadKabeer)
        
        val compatibilityResult = compatibilityCalculator.calculate(
            adaadResult.destinyNumber, 
            adaadCalculator.calculate(input.dateOfBirth, motherAbjadKabeer).destinyNumber
        )
        
        val tashkheesResult = tashkheesCalculator.calculate(input.name)
        val falnamaResult = falnamaCalculator.calculate(nameAbjadKabeer)

        return JafrOutput(
            abjadKabeerTotal = nameAbjadKabeer,
            abjadSagheerTotal = abjadSagheer,
            abjadShamsiTotal = abjadShamsi,
            abjadQamariTotal = abjadQamari,
            ilmUlAdaadResult = adaadResult,
            takseerResult = takseerResult,
            transformationResult = transformationResult,
            ismResult = ismResult,
            ghalibMaghloobResult = ghalibMaghloobResult,
            compatibilityResult = compatibilityResult,
            rohaniTashkhees = tashkheesResult,
            falnama = falnamaResult
        )
    }
}
