package com.zakootaapps.ilmjafar.data.local.jafari_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "ism_ilahi_rules")
@Serializable
data class IsmIlahiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "muwakkil_rules")
@Serializable
data class MuwakkilEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "abjad_rules")
@Serializable
data class AbjadRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "noorani_letters")
@Serializable
data class NooraniLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "zulmani_letters")
@Serializable
data class ZulmaniLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "fire_letters")
@Serializable
data class FireLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "water_letters")
@Serializable
data class WaterLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "air_letters")
@Serializable
data class AirLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "earth_letters")
@Serializable
data class EarthLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "planet_rules")
@Serializable
data class PlanetRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "day_rules")
@Serializable
data class DayRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "hour_rules")
@Serializable
data class HourRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "shape_rules")
@Serializable
data class ShapeRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "writing_direction_rules")
@Serializable
data class WritingDirectionRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "ink_rules")
@Serializable
data class InkRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "paper_rules")
@Serializable
data class PaperRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "purpose_rules")
@Serializable
data class PurposeRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "classical_talism_templates_v2")
@Serializable
data class ClassicalTalismTemplateEntity2(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "classical_naqoosh_templates_v2")
@Serializable
data class ClassicalNaqooshTemplateEntity2(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "element_compatibility")
@Serializable
data class ElementCompatibilityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "planet_compatibility")
@Serializable
data class PlanetCompatibilityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "letter_harmony_rules")
@Serializable
data class LetterHarmonyRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "letter_conflict_rules")
@Serializable
data class LetterConflictRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "numerical_harmony_rules")
@Serializable
data class NumericalHarmonyRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)

@Entity(tableName = "numerical_conflict_rules")
@Serializable
data class NumericalConflictRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val description: String = "",
    val reference: String = "",
    val category: String = "",
    val tags: String = "",
    val priority: Int = 0,
    val status: String = ""
)


@Entity(tableName = "arabic_letters_master")
@Serializable
data class ArabicLetterMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicLetter: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val unicodeValue: String = "",
    val letterOrder: Int = 0,
    val pronunciation: String = "",
    val formBeginning: String = "",
    val formMiddle: String = "",
    val formEnd: String = "",
    val formIsolated: String = "",

    // ABJAD
    val abjadKabir: Int = 0,
    val abjadSaghir: Int = 0,
    val abjadQamari: Int = 0,
    val abjadShamsi: Int = 0,
    val misriValue: Int = 0,
    val maghribiValue: Int = 0,
    val alternativeValues: String = "",

    // ELEMENT
    val dominantElement: String = "",
    val secondaryElement: String = "",
    val nature: String = "",
    val isHot: Boolean = false,
    val isCold: Boolean = false,
    val isDry: Boolean = false,
    val isWet: Boolean = false,

    // PLANETS
    val rulingPlanet: String = "",
    val secondaryPlanet: String = "",
    val planetStrength: String = "",
    val planetRank: String = "",
    val planetNature: String = "",

    // COLOURS
    val primaryColour: String = "",
    val secondaryColour: String = "",
    val inkColour: String = "",
    val paperColour: String = "",
    val writingColour: String = "",

    // DIRECTIONS
    val writingDirection: String = "",
    val elementDirection: String = "",
    val planetDirection: String = "",
    val facingDirection: String = "",

    // TIME
    val bestHour: String = "",
    val bestDay: String = "",
    val bestPlanetaryHour: String = "",
    val bestLunarPhase: String = "",
    val bestMoonMansion: String = "",

    // SPIRITUAL
    val associatedIsmIlahi: String = "",
    val associatedDivineAttribute: String = "",
    val associatedAngel: String = "",
    val associatedMuwakkil: String = "",
    val associatedRijalUlGhaib: String = "",
    val associatedKhadim: String = "",

    // RITUAL
    val recommendedBakhoor: String = "",
    val recommendedPerfume: String = "",
    val recommendedMetal: String = "",
    val recommendedStone: String = "",
    val recommendedCloth: String = "",
    val recommendedPen: String = "",
    val recommendedPaper: String = "",

    // USAGE
    val talismUsage: String = "",
    val naqooshUsage: String = "",
    val jafrUsage: String = "",
    val takseerUsage: String = "",
    val compatibilityUsage: String = "",
    val healingUsage: String = "",
    val protectionUsage: String = "",
    val loveUsage: String = "",
    val rizqUsage: String = "",
    val businessUsage: String = "",
    val travelUsage: String = "",
    val knowledgeUsage: String = "",

    // COMPATIBILITY
    val compatibleLetters: String = "",
    val conflictingLetters: String = "",
    val friendlyElements: String = "",
    val enemyElements: String = "",
    val friendlyPlanets: String = "",
    val enemyPlanets: String = "",

    // ADVANCED
    val hiddenProperties: String = "",
    val secretMeanings: String = "",
    val jamaliProperties: String = "",
    val jalaliProperties: String = "",
    val nuraniProperties: String = "",
    val zulmaniProperties: String = "",
    val positiveEffects: String = "",
    val negativeEffects: String = "",
    val warnings: String = "",
    val restrictions: String = "",

    // REFERENCES
    val primaryReference: String = "",
    val secondaryReference: String = "",
    val bookName: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val edition: String = "",
    val authenticityLevel: String = "",
    val sourceUrl: String = "",
    val referenceNotes: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val verificationStatus: String = "Needs Review",
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val isRejected: Boolean = false,
    val importedBy: String = "",
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "muwakkil_master")
@Serializable
data class MuwakkilMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val knownTitles: String = "",
    val alternativeNames: String = "",
    val category: String = "",
    val description: String = "",

    // SPIRITUAL
    val associatedIsmIlahi: String = "",
    val associatedArabicLetters: String = "",
    val dominantElement: String = "",
    val secondaryElement: String = "",
    val planet: String = "",
    val planetStrength: String = "",
    val temperament: String = "",
    val isJamali: Boolean = false,
    val isJalali: Boolean = false,
    val isNurani: Boolean = false,

    // JAFR
    val abjadRelation: String = "",
    val qamariRelation: String = "",
    val shamsiRelation: String = "",
    val saghirRelation: String = "",
    val kabirRelation: String = "",

    // TIME
    val bestDay: String = "",
    val bestPlanetaryHour: String = "",
    val bestMoonMansion: String = "",
    val bestLunarPhase: String = "",

    // RITUAL
    val recommendedInk: String = "",
    val recommendedPaper: String = "",
    val recommendedBakhoor: String = "",
    val recommendedPerfume: String = "",
    val recommendedDirection: String = "",
    val recommendedMetal: String = "",
    val recommendedStone: String = "",

    // USAGE
    val talismUsage: String = "",
    val naqooshUsage: String = "",
    val healingUsage: String = "",
    val protectionUsage: String = "",
    val loveUsage: String = "",
    val rizqUsage: String = "",
    val knowledgeUsage: String = "",
    val businessUsage: String = "",
    val enemyProtectionUsage: String = "",
    val spiritualPresenceUsage: String = "",

    // LINKS
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedIsmIlahi: String = "",
    val relatedPlanet: String = "",
    val relatedElement: String = "",
    val relatedArabicLetters: String = "",

    // REFERENCES
    val primarySource: String = "",
    val secondarySource: String = "",
    val book: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val authenticity: String = "",
    val sourceUrl: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "planet_master")
@Serializable
data class PlanetMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val alternativeNames: String = "",
    val description: String = "",

    // ASTROLOGY
    val planetNumber: Int = 0,
    val planetRank: String = "",
    val planetNature: String = "",
    val planetGender: String = "",
    val beneficOrMalefic: String = "",
    val friendlyPlanets: String = "",
    val enemyPlanets: String = "",
    val neutralPlanets: String = "",
    val exaltation: String = "",
    val debilitation: String = "",
    val ownSigns: String = "",
    val moolTrikona: String = "",

    // SPIRITUAL
    val associatedIsmIlahi: String = "",
    val associatedMuwakkil: String = "",
    val associatedAngels: String = "",
    val associatedLetters: String = "",
    val associatedNumbers: String = "",
    val associatedAbjad: String = "",
    val associatedColour: String = "",
    val associatedInk: String = "",
    val associatedPerfume: String = "",
    val associatedBakhoor: String = "",
    val associatedMetal: String = "",
    val associatedStone: String = "",
    val associatedAnimal: String = "",

    // ELEMENT
    val primaryElement: String = "",
    val secondaryElement: String = "",
    val temperament: String = "",
    val isHot: Boolean = false,
    val isCold: Boolean = false,
    val isDry: Boolean = false,
    val isWet: Boolean = false,

    // TIME
    val planetDay: String = "",
    val planetHour: String = "",
    val planetCycle: String = "",
    val strengthTimes: String = "",
    val weakTimes: String = "",

    // RITUAL
    val recommendedDirection: String = "",
    val recommendedPaper: String = "",
    val recommendedTalismShape: String = "",
    val recommendedNaqoosh: String = "",
    val recommendedRituals: String = "",

    // LINKS
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedIsmIlahi: String = "",
    val relatedMuwakkilat: String = "",
    val relatedElements: String = "",
    val relatedZodiacSigns: String = "",
    val relatedMoonMansions: String = "",

    // REFERENCES
    val primarySource: String = "",
    val secondarySource: String = "",
    val book: String = "",
    val author: String = "",
    val page: String = "",
    val sourceUrl: String = "",
    val authenticity: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "element_master")
@Serializable
data class ElementMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val alternativeNames: String = "",
    val description: String = "",
    val category: String = "",

    // SPIRITUAL
    val associatedIsmIlahi: String = "",
    val associatedMuwakkilat: String = "",
    val associatedAngels: String = "",
    val associatedArabicLetters: String = "",
    val associatedNumbers: String = "",
    val associatedColours: String = "",
    val associatedIncense: String = "",
    val associatedPerfume: String = "",
    val associatedMetal: String = "",
    val associatedStone: String = "",

    // ASTROLOGY
    val associatedPlanets: String = "",
    val friendlyElements: String = "",
    val enemyElements: String = "",
    val neutralElements: String = "",
    val associatedZodiacSigns: String = "",
    val associatedMoonMansions: String = "",

    // TEMPERAMENT
    val isHot: Boolean = false,
    val isCold: Boolean = false,
    val isDry: Boolean = false,
    val isWet: Boolean = false,
    val dominantNature: String = "",
    val positiveTraits: String = "",
    val negativeTraits: String = "",

    // APPLICATIONS
    val healingUsage: String = "",
    val protectionUsage: String = "",
    val loveUsage: String = "",
    val businessUsage: String = "",
    val knowledgeUsage: String = "",
    val spiritualGrowthUsage: String = "",
    val talismUsage: String = "",
    val naqooshUsage: String = "",

    // LINKS
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedPlanets: String = "",
    val relatedMuwakkilat: String = "",
    val relatedIsmIlahi: String = "",
    val relatedAbjadRules: String = "",

    // REFERENCES
    val primarySource: String = "",
    val secondarySource: String = "",
    val book: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val authenticity: String = "",
    val trustedUrl: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "moon_mansion_master")
@Serializable
data class MoonMansionMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val alternativeNames: String = "",
    val mansionNumber: Int = 0,
    val description: String = "",

    // ASTRONOMY
    val startDegree: String = "",
    val endDegree: String = "",
    val zodiacSign: String = "",
    val visibleStars: String = "",
    val constellation: String = "",

    // SPIRITUAL
    val nature: String = "",
    val temperament: String = "",
    val dominantElement: String = "",
    val associatedPlanet: String = "",
    val associatedIsmIlahi: String = "",
    val associatedMuwakkil: String = "",
    val associatedAngels: String = "",
    val associatedArabicLetters: String = "",

    // JAFR
    val abjadRelation: String = "",
    val elementRelation: String = "",
    val planetRelation: String = "",
    val compatibilityScore: String = "",

    // TIME
    val bestUses: String = "",
    val forbiddenUses: String = "",
    val bestDay: String = "",
    val bestPlanetaryHour: String = "",
    val moonStrength: String = "",
    val moonWeakness: String = "",

    // APPLICATIONS
    val talismUsage: String = "",
    val naqooshUsage: String = "",
    val healingUsage: String = "",
    val loveUsage: String = "",
    val marriageUsage: String = "",
    val businessUsage: String = "",
    val travelUsage: String = "",
    val knowledgeUsage: String = "",
    val enemyProtectionUsage: String = "",
    val rizqUsage: String = "",
    val spiritualPresenceUsage: String = "",

    // RITUAL
    val recommendedInk: String = "",
    val recommendedPaper: String = "",
    val recommendedPerfume: String = "",
    val recommendedBakhoor: String = "",
    val recommendedDirection: String = "",
    val recommendedShape: String = "",

    // LINKS
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedPlanet: String = "",
    val relatedElement: String = "",
    val relatedMuwakkilat: String = "",
    val relatedIsmIlahi: String = "",
    val relatedZodiacSign: String = "",

    // REFERENCES
    val primarySource: String = "",
    val secondarySource: String = "",
    val book: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val authenticity: String = "",
    val trustedUrl: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "planetary_hour_master")
@Serializable
data class PlanetaryHourMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val planet: String = "",
    val hourNumber: Int = 0,
    val description: String = "",

    // ASTRONOMY
    val planetStrength: String = "",
    val planetWeakness: String = "",
    val dayRelation: String = "",
    val nightRelation: String = "",

    // SPIRITUAL
    val associatedIsmIlahi: String = "",
    val associatedMuwakkil: String = "",
    val associatedElement: String = "",
    val associatedColour: String = "",
    val associatedBakhoor: String = "",
    val associatedPerfume: String = "",
    val associatedInk: String = "",
    val associatedMetal: String = "",
    val associatedStone: String = "",

    // APPLICATIONS
    val healingUsage: String = "",
    val loveUsage: String = "",
    val marriageUsage: String = "",
    val businessUsage: String = "",
    val travelUsage: String = "",
    val knowledgeUsage: String = "",
    val enemyProtectionUsage: String = "",
    val talismUsage: String = "",
    val naqooshUsage: String = "",

    // PROHIBITIONS
    val forbiddenWorks: String = "",
    val weakUses: String = "",
    val warnings: String = "",

    // LINKS
    val relatedPlanet: String = "",
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedMoonMansion: String = "",
    val relatedElements: String = "",

    // REFERENCES
    val primarySource: String = "",
    val secondarySource: String = "",
    val book: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val authenticity: String = "",
    val trustedUrl: String = "",

    // AI
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "abjad_master")
@Serializable
data class AbjadMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicLetter: String = "",
    val urduLetter: String = "",
    val englishTransliteration: String = "",
    val unicode: String = "",
    val letterPosition: Int = 0,

    // VALUES
    val abjadKabeerValue: Int = 0,
    val abjadSagheerValue: Int = 0,
    val abjadQamari: Int = 0,
    val abjadShamsi: Int = 0,
    val numericValue: Int = 0,

    // ASTROLOGY & TEMPERAMENT
    val element: String = "",
    val planet: String = "",
    val zodiac: String = "",
    val temperament: String = "",
    val isHot: Boolean = false,
    val isCold: Boolean = false,
    val isDry: Boolean = false,
    val isWet: Boolean = false,

    // SPIRITUAL NATURE
    val isJalali: Boolean = false,
    val isJamali: Boolean = false,
    val isMushtarik: Boolean = false,

    // RELATIONS
    val ismIlahiRelation: String = "",
    val muwakkilRelation: String = "",
    val rijalUlGhaibRelation: String = "",
    val planetaryHourRelation: String = "",
    val bakhoorRelation: String = "",
    val perfumeRelation: String = "",
    val gemstoneRelation: String = "",

    // RITUALS
    val suitableInkColor: String = "",
    val suitablePaperColor: String = "",
    val recommendedTime: String = "",
    val suitableMoonPhase: String = "",
    val suitablePlanetaryDay: String = "",
    val recommendedPlanetaryHour: String = "",

    // USES
    val spiritualUses: String = "",
    val healingUses: String = "",
    val talismUses: String = "",
    val naqooshUses: String = "",
    val jafrUses: String = "",
    val astrologyUses: String = "",

    // REFERENCES
    val referencesInfo: String = "",
    val source: String = "",

    // AI & TRACKING
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "jafr_rule_master")
@Serializable
data class JafrRuleMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ruleId: String = "",
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val ruleCategory: String = "",
    val ruleType: String = "",
    val ruleDescription: String = "",

    // HISTORY & REFS
    val historicalBackground: String = "",
    val classicalReference: String = "",
    val sourceBook: String = "",
    val author: String = "",
    val pageNumber: String = "",
    val madhhab: String = "",
    val authenticityLevel: String = "",

    // FORMULA ENGINE
    val formulaName: String = "",
    val formulaType: String = "",
    val inputParameters: String = "",
    val requiredInputs: String = "",
    val optionalInputs: String = "",
    val calculationFormula: String = "",
    val calculationSteps: String = "",
    val intermediateValues: String = "",
    val finalResult: String = "",
    val alternativeFormula: String = "",
    val exceptionRules: String = "",
    val validationRules: String = "",
    val priority: Int = 0,

    // AI & STATUS
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isApproved: Boolean = false,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L,

    // INPUTS SUPPORTED
    val supportedInputs: String = "",

    // OUTPUTS SUPPORTED
    val supportedOutputs: String = "",

    // RELATIONS
    val relatedAbjad: String = "",
    val relatedPlanet: String = "",
    val relatedElement: String = "",
    val relatedTemperament: String = "",
    val relatedMoonMansion: String = "",
    val relatedPlanetaryHour: String = "",
    val relatedIsmIlahi: String = "",
    val relatedMuwakkil: String = "",
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedBakhoor: String = "",
    val relatedPerfume: String = "",
    val relatedGemstone: String = "",
    val relatedDiagnosis: String = "",

    // AI ENGINE
    val aiExplanation: String = "",
    val aiReasoning: String = "",
    val stepByStepBreakdown: String = "",
    val alternativeInterpretation: String = "",
    val relatedRules: String = "",
    val similarRules: String = "",
    val recommendedNextRule: String = ""
)

@Entity(tableName = "gemstone_master")
@Serializable
data class GemstoneMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val scientificName: String = "",
    val tradeName: String = "",
    val alternativeNames: String = "",
    val category: String = "",
    val color: String = "",
    val transparency: String = "",
    val hardness: String = "",
    val chemicalFormula: String = "",
    val crystalSystem: String = "",
    val density: String = "",
    val luster: String = "",
    val originCountry: String = "",
    val isNatural: Boolean = true,
    val availability: String = "",

    // ASTROLOGY
    val planet: String = "",
    val planetStrength: String = "",
    val planetWeakness: String = "",
    val zodiacSigns: String = "",
    val compatibleElements: String = "",
    val compatibleTemperament: String = "",
    val compatiblePlanetaryDay: String = "",
    val compatiblePlanetaryHour: String = "",
    val compatibleMoonMansions: String = "",

    // SPIRITUAL
    val abjadRelation: String = "",
    val ismIlahiRelation: String = "",
    val muwakkilRelation: String = "",
    val rijalUlGhaibRelation: String = "",
    val talismRelation: String = "",
    val naqooshRelation: String = "",
    val healingRelation: String = "",
    val diagnosisRelation: String = "",
    val recommendedWazifa: String = "",
    val recommendedAmal: String = "",

    // WEARING RULES
    val recommendedFinger: String = "",
    val recommendedHand: String = "",
    val recommendedMetal: String = "",
    val recommendedWeight: String = "",
    val recommendedDay: String = "",
    val recommendedHour: String = "",
    val recommendedMoonPhase: String = "",
    val purificationMethod: String = "",
    val activationMethod: String = "",
    val duration: String = "",
    val removalConditions: String = "",
    val wearingWarnings: String = "",

    // HEALING
    val physicalBenefits: String = "",
    val mentalBenefits: String = "",
    val spiritualBenefits: String = "",
    val traditionalUses: String = "",
    val precautions: String = "",
    val sideEffects: String = "",
    val scientificNotes: String = "",

    // LINKS
    val relatedBakhoor: String = "",
    val relatedPerfume: String = "",
    val relatedPlanet: String = "",
    val relatedAbjad: String = "",
    val relatedJafrRule: String = "",
    val relatedElement: String = "",
    val relatedTemperament: String = "",
    val relatedDiagnosis: String = "",
    val relatedAstrology: String = "",
    val relatedPlanetaryHours: String = "",
    val relatedMoonMansions: String = "",

    // AI & TRACKING
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "metal_master")
@Serializable
data class MetalMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val scientificName: String = "",
    val chemicalSymbol: String = "",
    val atomicNumber: String = "",
    
    // SPIRITUAL & ASTROLOGY
    val planetRelation: String = "",
    val elementRelation: String = "",
    val temperament: String = "",

    // USES
    val healingUses: String = "",
    val talismUses: String = "",
    val naqooshUses: String = "",

    // RECOMMENDATIONS & WARNINGS
    val recommendedGemstones: String = "",
    val recommendedRituals: String = "",
    val warnings: String = "",
    
    // REFERENCES
    val referencesInfo: String = "",

    // AI & TRACKING
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "bakhoor_master")
@Serializable
data class BakhoorMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val botanicalName: String = "",
    val scientificName: String = "",
    val commonNames: String = "",
    val category: String = "",
    val originCountry: String = "",
    val region: String = "",
    val availability: String = "",

    // SPIRITUAL PROPERTIES
    val planetRelation: String = "",
    val elementRelation: String = "",
    val temperament: String = "",
    val moonMansionRelation: String = "",
    val planetaryDay: String = "",
    val planetaryHour: String = "",
    val abjadRelation: String = "",
    val ismIlahiRelation: String = "",
    val muwakkilRelation: String = "",
    val rijalUlGhaibRelation: String = "",
    val talismRelation: String = "",
    val naqooshRelation: String = "",
    val jafrRelation: String = "",

    // USES
    val healingUses: String = "",
    val spiritualUses: String = "",
    val meditation: String = "",
    val ruqyah: String = "",
    val protection: String = "",
    val love: String = "",
    val business: String = "",
    val prosperity: String = "",
    val knowledge: String = "",
    val dreamWork: String = "",
    val negativeEnergyRemoval: String = "",
    val evilEye: String = "",
    val jinnRelated: String = "",
    val generalWorship: String = "",
    val diagnosisSupport: String = "",

    // BURNING DETAILS
    val burningMethod: String = "",
    val recommendedQuantity: String = "",
    val recommendedTime: String = "",
    val recommendedDay: String = "",
    val recommendedHour: String = "",
    val recommendedDuration: String = "",
    val recommendedMetalBurner: String = "",
    val recommendedCharcoal: String = "",
    val warnings: String = "",
    val safetyInstructions: String = "",

    // LINKS
    val relatedGemstone: String = "",
    val relatedPerfume: String = "",
    val relatedMetal: String = "",
    val relatedPlanet: String = "",
    val relatedElement: String = "",
    val relatedDiagnosis: String = "",
    val relatedHealing: String = "",
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedAstrology: String = "",
    val relatedPlanetaryHours: String = "",
    val relatedMoonMansions: String = "",

    // AI & TRACKING
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)

@Entity(tableName = "perfume_master")
@Serializable
data class PerfumeMasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // GENERAL
    val arabicName: String = "",
    val urduName: String = "",
    val englishName: String = "",
    val botanicalName: String = "",
    val scientificName: String = "",
    val commonName: String = "",
    val perfumeType: String = "",
    val sourceMaterial: String = "",
    val extractionMethod: String = "",
    val originCountry: String = "",
    val region: String = "",
    val manufacturer: String = "",

    // SPIRITUAL PROPERTIES
    val planetRelation: String = "",
    val elementRelation: String = "",
    val temperament: String = "",
    val moonMansionRelation: String = "",
    val planetaryDay: String = "",
    val planetaryHour: String = "",
    val abjadRelation: String = "",
    val ismIlahiRelation: String = "",
    val muwakkilRelation: String = "",
    val rijalUlGhaibRelation: String = "",
    val talismRelation: String = "",
    val naqooshRelation: String = "",
    val jafrRelation: String = "",

    // USES
    val healingUses: String = "",
    val spiritualUses: String = "",
    val meditation: String = "",
    val prayer: String = "",
    val dhikr: String = "",
    val ruqyah: String = "",
    val love: String = "",
    val business: String = "",
    val prosperity: String = "",
    val knowledge: String = "",
    val dreamWork: String = "",
    val negativeEnergyRemoval: String = "",
    val evilEye: String = "",
    val generalWorship: String = "",
    val diagnosisSupport: String = "",

    // APPLICATION
    val howToApply: String = "",
    val bodyPoints: String = "",
    val bestTime: String = "",
    val bestDay: String = "",
    val recommendedQuantity: String = "",
    val recommendedDuration: String = "",
    val storageMethod: String = "",
    val shelfLife: String = "",
    val warnings: String = "",
    val safetyInstructions: String = "",

    // LINKS
    val relatedGemstone: String = "",
    val relatedMetal: String = "",
    val relatedPlanet: String = "",
    val relatedElement: String = "",
    val relatedBakhoor: String = "",
    val relatedPlanetaryHours: String = "",
    val relatedMoonMansions: String = "",
    val relatedDiagnosis: String = "",
    val relatedHealing: String = "",
    val relatedTalism: String = "",
    val relatedNaqoosh: String = "",
    val relatedAstrology: String = "",

    // AI & TRACKING
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val importedDate: Long = 0L,
    val updatedDate: Long = 0L
)
