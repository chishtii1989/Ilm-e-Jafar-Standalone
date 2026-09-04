package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.data

/**
 * Centralized Repository for Verified Reverse-Engineered Abjad Data.
 * Serves as the single source of truth for the Ilm-ul-Abjad and Jafar engine.
 */
object AbjadRepository {

    // 1. Standard Abjad Values (Abjad-e-Kabeer)
    val abjadKabeer: Map<Char, Int> = mapOf(
        'ا' to 1, 'ب' to 2, 'ج' to 3, 'د' to 4, 'ہ' to 5, 'و' to 6, 'ز' to 7, 'ح' to 8, 'ط' to 9, 'ی' to 10,
        'ک' to 20, 'ل' to 30, 'م' to 40, 'ن' to 50, 'س' to 60, 'ع' to 70, 'ف' to 80, 'ص' to 90,
        'ق' to 100, 'ر' to 200, 'ش' to 300, 'ت' to 400, 'ث' to 500, 'خ' to 600, 'ذ' to 700, 'ض' to 800,
        'ظ' to 900, 'غ' to 1000
    )

    // 2. Abjad-e-Sagheer
    val abjadSagheer: Map<Char, Int> = mapOf(
        'ا' to 1, 'ب' to 2, 'ج' to 3, 'د' to 4, 'ہ' to 5, 'و' to 6, 'ز' to 7, 'ح' to 8, 'ط' to 9, 'ی' to 1,
        'ک' to 2, 'ل' to 3, 'م' to 4, 'ن' to 5, 'س' to 6, 'ع' to 7, 'ف' to 8, 'ص' to 9,
        'ق' to 1, 'ر' to 2, 'ش' to 3, 'ت' to 4, 'ث' to 5, 'خ' to 6, 'ذ' to 7, 'ض' to 8,
        'ظ' to 9, 'غ' to 1
    )

    // 3. Arabic Letters
    val arabicLetters: List<Char> = listOf(
        'ا', 'ب', 'ج', 'د', 'ہ', 'و', 'ز', 'ح', 'ط', 'ی', 'ک', 'ل', 'م', 'ن',
        'س', 'ع', 'ف', 'ص', 'ق', 'ر', 'ش', 'ت', 'ث', 'خ', 'ذ', 'ض', 'ظ', 'غ'
    )

    // 4. Urdu Letters Normalization Mapping
    val urduNormalizationMap: Map<Char, Char> = mapOf(
        'آ' to 'ا', 'أ' to 'ا', 'إ' to 'ا',
        'ة' to 'ہ', 'ھ' to 'ہ',
        'ؤ' to 'و',
        'ئ' to 'ی', 'ى' to 'ی', 'ے' to 'ی',
        'ٹ' to 'ت',
        'ڈ' to 'د',
        'ڑ' to 'ر',
        'ں' to 'ن',
        'گ' to 'ک',
        'چ' to 'ج',
        'پ' to 'ب',
        'ژ' to 'ز'
    )

    // 5 & 6. Nuqaat (Arabic & Urdu)
    val nuqaatMap: Map<Char, Int> = mapOf(
        'ا' to 0, 'آ' to 0, 'أ' to 0, 'إ' to 0,
        'ب' to 1, 'پ' to 3, 'ت' to 2, 'ٹ' to 0, 'ث' to 3,
        'ج' to 1, 'چ' to 3, 'ح' to 0, 'خ' to 1,
        'د' to 0, 'ڈ' to 0, 'ذ' to 1,
        'ر' to 0, 'ڑ' to 0, 'ز' to 1, 'ژ' to 3,
        'س' to 0, 'ش' to 3, 'ص' to 0, 'ض' to 1,
        'ط' to 0, 'ظ' to 1, 'ع' to 0, 'غ' to 1,
        'ف' to 1, 'ق' to 2, 'ک' to 0, 'گ' to 0,
        'ل' to 0, 'م' to 0, 'ن' to 1, 'ں' to 0,
        'و' to 0, 'ؤ' to 0, 'ہ' to 0, 'ھ' to 0, 'ة' to 2,
        'ی' to 2, 'ئ' to 2, 'ے' to 0, 'ء' to 0
    )

    // 7. Marateb (Derived dynamically by length of value, but here as a lookup if needed)
    val maratebMap: Map<Char, Int> = abjadKabeer.mapValues { it.value.toString().length }

    // 8. Baduh Arrays
    val baduhArray: List<Char> = listOf('ب', 'د', 'و', 'ح')

    // 9. Elements (Fire, Air, Water, Earth)
    val fireLetters: List<Char> = listOf('ا', 'ہ', 'ط', 'م', 'ف', 'ش', 'ذ')
    val airLetters: List<Char> = listOf('ب', 'و', 'ی', 'ن', 'ص', 'ت', 'ض')
    val waterLetters: List<Char> = listOf('ج', 'ز', 'ک', 'س', 'ق', 'ث', 'ظ')
    val earthLetters: List<Char> = listOf('د', 'ح', 'ل', 'ع', 'ر', 'خ', 'غ')
    
    val elementByLetter: Map<Char, String> = buildMap {
        fireLetters.forEach { put(it, "آتش") }
        airLetters.forEach { put(it, "باد") }
        waterLetters.forEach { put(it, "آب") }
        earthLetters.forEach { put(it, "خاک") }
    }

    // 10. Planet Groups
    val planets: List<String> = listOf("زحل", "مشتری", "مریخ", "شمس", "زہرہ", "عطارد", "قمر")

    // 11. Zodiac Groups
    val zodiacs: List<String> = listOf("حمل", "ثور", "جوزا", "سرطان", "اسد", "سنبلہ", "میزان", "عقرب", "قوس", "جدی", "دلو", "حوت")

    // 12. Noorani Letters
    val nooraniLetters: List<Char> = listOf('ا', 'ح', 'ر', 'س', 'ص', 'ط', 'ع', 'ق', 'ک', 'ل', 'م', 'ن', 'ہ', 'ی')

    // 13. Zulmani Letters
    val zulmaniLetters: List<Char> = arabicLetters.filter { it !in nooraniLetters }

    // 14. Malfoozi Letters (Spoken representation)
    val malfooziLetters: Map<Char, String> = mapOf(
        'ا' to "الف", 'ب' to "با", 'ج' to "جیم", 'د' to "دال", 'ہ' to "ہا",
        'و' to "واو", 'ز' to "زا", 'ح' to "حا", 'ط' to "طا", 'ی' to "یا",
        'ک' to "کاف", 'ل' to "لام", 'م' to "میم", 'ن' to "نون", 'س' to "سین",
        'ع' to "عین", 'ف' to "فا", 'ص' to "صاد", 'ق' to "قاف", 'ر' to "را",
        'ش' to "شین", 'ت' to "تا", 'ث' to "ثا", 'خ' to "خا", 'ذ' to "ذال",
        'ض' to "ضاد", 'ظ' to "ظا", 'غ' to "غین"
    )

    // 15. Maktoobi Letters (Written representation)
    val maktoobiLetters: Map<Char, Char> = arabicLetters.associateWith { it }

    // 16. Mustantaqa (Numeric values converted back to Letters)
    val mustantaqaMap: Map<Int, Char> = abjadKabeer.entries.associate { (k, v) -> v to k }

    // 17. Malfooze (Length of the spoken letters)
    val malfoozeLengthMap: Map<Char, Int> = malfooziLetters.mapValues { it.value.length }

    // 18. Haroof Groups (Lookup arrays)
    val inkGroups: List<String> = listOf("مشک و زعفران", "زرد رنگ", "سرخ روشنائی", "عرق گلاب اور زعفران", "سیاہ روشنائی", "سبز روشنائی", "نیلی روشنائی")
    val paperGroups: List<String> = listOf("سفید کاغذ", "زرد کاغذ", "سرخ کاغذ", "بھوج پتر", "ہرن کی جھلی", "چاندی کا پتر", "تانبے کا پتر")
    val bakhoorGroups: List<String> = listOf("عود و عنبر", "لوبان", "صندل سرخ", "کافور", "حنا", "جاوتری", "مستگی")
    val directionGroups: List<String> = listOf("مشرق", "مغرب", "شمال", "جنوب")
    val dayGroups: List<String> = listOf("اتوار", "پیر", "منگل", "بدھ", "جمعرات", "جمعہ", "ہفتہ")

    // Helper to get normalized char
    fun getNormalizedChar(char: Char): Char = urduNormalizationMap[char] ?: char

    // Helper to get Abjad Kabeer value
    fun getAbjadKabeerValue(char: Char): Int = abjadKabeer[getNormalizedChar(char)] ?: 0
}
