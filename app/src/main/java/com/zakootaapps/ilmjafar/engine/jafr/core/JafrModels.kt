package com.zakootaapps.ilmjafar.engine.jafr.core

data class JafrInput(
    val name: String,
    val motherName: String,
    val dateOfBirth: String, // e.g., "YYYY-MM-DD"
    val timeOfBirth: String, // e.g., "HH:mm"
    val placeOfBirth: String,
    val gender: String
)

data class JafrOutput(
    val abjadKabeerTotal: Int,
    val abjadSagheerTotal: Int,
    val abjadShamsiTotal: Int,
    val abjadQamariTotal: Int,
    val ilmUlAdaadResult: AdaadResult,
    val takseerResult: TakseerResult,
    val transformationResult: TransformationResult,
    val ismResult: IsmResult,
    val ghalibMaghloobResult: GhalibMaghloobResult,
    val compatibilityResult: CompatibilityResult,
    val rohaniTashkhees: RohaniTashkheesResult,
    val falnama: FalnamaResult
)

// Sub-results
data class AdaadResult(
    val destinyNumber: Int,
    val soulUrgeNumber: Int,
    val personalityNumber: Int
)

data class TakseerResult(
    val haroof: List<String>,
    val sawamit: List<String>,
    val musawitat: List<String>
)

data class TransformationResult(
    val bast: List<String>,
    val qabz: List<String>,
    val qalb: List<String>,
    val aks: List<String>
)

data class IsmResult(
    val istikhrajEIsm: List<String>,
    val ismEAzam: List<String>
)

data class GhalibMaghloobResult(
    val status: String, // "Ghalib" or "Maghloob"
    val details: String
)

data class CompatibilityResult(
    val haroofScore: Int,
    val numbersScore: Int,
    val analysis: String
)

data class RohaniTashkheesResult(
    val elements: Map<String, Int>,
    val dominantElement: String,
    val diagnosis: String
)

data class FalnamaResult(
    val prediction: String,
    val guidance: String
)
