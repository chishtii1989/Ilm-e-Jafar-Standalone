package com.zakootaapps.ilmjafar.data.repository

import com.zakootaapps.ilmjafar.data.local.TalismTemplateDao
import com.zakootaapps.ilmjafar.data.local.TalismTemplateEntity
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray
import org.json.JSONObject

class TalismTemplateRepository(private val talismTemplateDao: TalismTemplateDao) {

    fun getAllTemplatesFlow(): Flow<List<TalismTemplateEntity>> = talismTemplateDao.getAllTemplatesFlow()

    suspend fun getAllTemplates(): List<TalismTemplateEntity> = talismTemplateDao.getAllTemplates()

    suspend fun getTemplateByCategory(category: String): TalismTemplateEntity? {
        return talismTemplateDao.getTemplateByCategory(category)
    }

    suspend fun insertTemplate(template: TalismTemplateEntity) {
        talismTemplateDao.insertTemplate(template)
    }

    suspend fun updateTemplate(template: TalismTemplateEntity) {
        talismTemplateDao.updateTemplate(template)
    }

    suspend fun deleteTemplate(template: TalismTemplateEntity) {
        talismTemplateDao.deleteTemplate(template)
    }

    suspend fun importTemplatesFromJson(jsonString: String) {
        try {
            val templates = mutableListOf<TalismTemplateEntity>()
            val array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                templates.add(
                    TalismTemplateEntity(
                        category = obj.getString("category"),
                        purposeDescription = obj.getString("purposeDescription"),
                        arabicTawkeel = obj.getString("arabicTawkeel"),
                        alternativeTawkeel = obj.getString("alternativeTawkeel"),
                        suitableQuranicVerse = obj.getString("suitableQuranicVerse"),
                        suitableDivineName = obj.getString("suitableDivineName"),
                        alternativeDivineName = obj.getString("alternativeDivineName"),
                        suitablePlanet = obj.getString("suitablePlanet"),
                        suitableElement = obj.getString("suitableElement"),
                        suitableZodiac = obj.getString("suitableZodiac"),
                        suitableDay = obj.getString("suitableDay"),
                        suitableHour = obj.getString("suitableHour"),
                        suitableInkColor = obj.getString("suitableInkColor"),
                        suitablePaperColor = obj.getString("suitablePaperColor"),
                        suitableBakhoor = obj.getString("suitableBakhoor"),
                        writingDirection = obj.getString("writingDirection"),
                        recommendedShape = obj.getString("recommendedShape"),
                        recommendedBorderStyle = obj.getString("recommendedBorderStyle"),
                        recommendedSecretSymbol = obj.getString("recommendedSecretSymbol"),
                        recommendedSeal = obj.getString("recommendedSeal"),
                        difficultyLevel = obj.getString("difficultyLevel"),
                        specialNotes = obj.getString("specialNotes")
                    )
                )
            }
            talismTemplateDao.insertTemplates(templates)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun exportTemplatesToJson(): String {
        val templates = talismTemplateDao.getAllTemplates()
        val array = JSONArray()
        for (t in templates) {
            val obj = JSONObject()
            obj.put("category", t.category)
            obj.put("purposeDescription", t.purposeDescription)
            obj.put("arabicTawkeel", t.arabicTawkeel)
            obj.put("alternativeTawkeel", t.alternativeTawkeel)
            obj.put("suitableQuranicVerse", t.suitableQuranicVerse)
            obj.put("suitableDivineName", t.suitableDivineName)
            obj.put("alternativeDivineName", t.alternativeDivineName)
            obj.put("suitablePlanet", t.suitablePlanet)
            obj.put("suitableElement", t.suitableElement)
            obj.put("suitableZodiac", t.suitableZodiac)
            obj.put("suitableDay", t.suitableDay)
            obj.put("suitableHour", t.suitableHour)
            obj.put("suitableInkColor", t.suitableInkColor)
            obj.put("suitablePaperColor", t.suitablePaperColor)
            obj.put("suitableBakhoor", t.suitableBakhoor)
            obj.put("writingDirection", t.writingDirection)
            obj.put("recommendedShape", t.recommendedShape)
            obj.put("recommendedBorderStyle", t.recommendedBorderStyle)
            obj.put("recommendedSecretSymbol", t.recommendedSecretSymbol)
            obj.put("recommendedSeal", t.recommendedSeal)
            obj.put("difficultyLevel", t.difficultyLevel)
            obj.put("specialNotes", t.specialNotes)
            array.put(obj)
        }
        return array.toString()
    }

    suspend fun ensureDefaultTemplates() {
        val existing = talismTemplateDao.getAllTemplates()
        if (existing.isEmpty()) {
            val defaults = getDefaultTemplates()
            talismTemplateDao.insertTemplates(defaults)
        }
    }

    suspend fun analyzeAndMapPurpose(customPurpose: String): TalismTemplateEntity {
        val allTemplates = talismTemplateDao.getAllTemplates()
        
        // Simple heuristic matching
        var bestMatch: TalismTemplateEntity? = null
        var maxMatches = 0
        
        for (template in allTemplates) {
            val categoryWords = template.category.lowercase().split(" ", "_")
            val purposeWords = template.purposeDescription.lowercase().split(" ")
            
            var matches = 0
            for (word in customPurpose.lowercase().split(" ")) {
                if (word.length > 3) {
                    if (categoryWords.any { it.contains(word) }) matches += 2
                    if (purposeWords.any { it.contains(word) }) matches += 1
                }
            }
            if (matches > maxMatches) {
                maxMatches = matches
                bestMatch = template
            }
        }
        
        if (bestMatch != null && maxMatches > 0) {
            return bestMatch
        }
        
        // Return temporary custom profile
        return TalismTemplateEntity(
            category = "Custom (${customPurpose.take(20)})",
            purposeDescription = customPurpose,
            arabicTawkeel = "توكلوا يا خدام هذا الطلسم ب" + customPurpose,
            alternativeTawkeel = "يا خدام هذا العمل اقضوا حاجتي وهي " + customPurpose,
            suitableQuranicVerse = "وَقَالَ رَبُّكُمُ ادْعُونِي أَسْتَجِبْ لَكُمْ",
            suitableDivineName = "يا مجيب يا وهاب",
            alternativeDivineName = "يا سميع يا بصير",
            suitablePlanet = "مشتری",
            suitableElement = "آتشی",
            suitableZodiac = "حمل",
            suitableDay = "جمعرات",
            suitableHour = "مشتری",
            suitableInkColor = "زعفران و گلاب",
            suitablePaperColor = "سفید",
            suitableBakhoor = "عود و لوبان",
            writingDirection = "مشرق",
            recommendedShape = "مربع",
            recommendedBorderStyle = "سادہ لائن",
            recommendedSecretSymbol = "ستارہ",
            recommendedSeal = "خاتم سلیمانی",
            difficultyLevel = "General",
            specialNotes = "Temporary custom profile created based on user input."
        )
    }

    private fun getDefaultTemplates(): List<TalismTemplateEntity> {
        return listOf(
            TalismTemplateEntity(
                category = "Marriage",
                purposeDescription = "For arranging a good marriage, overcoming obstacles in getting married.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بجلب الزوج الصالح / الزوجة الصالحة لفلان بن فلانة",
                alternativeTawkeel = "أجيبوا يا أرواح وروحانيات هذا الطلسم وسهلوا أمر الزواج",
                suitableQuranicVerse = "وَمِنْ آيَاتِهِ أَنْ خَلَقَ لَكُم مِّنْ أَنفُسِكُمْ أَزْوَاجًا",
                suitableDivineName = "يا ودود يا جامع",
                alternativeDivineName = "يا لطيف يا حليم",
                suitablePlanet = "زہرہ (Venus)",
                suitableElement = "آبی (Water)",
                suitableZodiac = "میزان (Libra)",
                suitableDay = "جمعہ (Friday)",
                suitableHour = "زہرہ (Venus Hour)",
                suitableInkColor = "سرخ (Red)",
                suitablePaperColor = "سفید یا گلابی (White or Pink)",
                suitableBakhoor = "صندل سرخ، گلاب (Red Sandalwood, Rose)",
                writingDirection = "مغرب (West)",
                recommendedShape = "مربع (Square) / ہشت پہلو (Octagon)",
                recommendedBorderStyle = "نقوش و نگار (Floral lines)",
                recommendedSecretSymbol = "دل یا ستارہ (Heart or Star)",
                recommendedSeal = "خاتم زہرہ (Seal of Venus)",
                difficultyLevel = "Medium",
                specialNotes = "Best performed during the waxing moon."
            ),
            TalismTemplateEntity(
                category = "Love",
                purposeDescription = "To create affection and love between two people.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بإلقاء المحبة والمودة في قلب فلان بن فلانة لمحبة فلان بن فلانة",
                alternativeTawkeel = "اعطفوا قلب فلان بن فلانة على فلان بن فلانة",
                suitableQuranicVerse = "يُحِبُّونَهُمْ كَحُبِّ اللَّهِ وَالَّذِينَ آمَنُوا أَشَدُّ حُبًّا لِّلَّهِ",
                suitableDivineName = "يا ودود يا رؤوف",
                alternativeDivineName = "يا رحيم يا عطوف",
                suitablePlanet = "زہرہ (Venus)",
                suitableElement = "ہوائی (Air)",
                suitableZodiac = "ثور (Taurus)",
                suitableDay = "جمعہ (Friday)",
                suitableHour = "زہرہ (Venus Hour)",
                suitableInkColor = "سرخ زعفران (Red Saffron)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "عود، عنبر، لبان ذکر (Oud, Amber, Frankincense)",
                writingDirection = "مغرب (West)",
                recommendedShape = "دائرہ (Circle)",
                recommendedBorderStyle = "دائرہ نورانی (Luminous circle)",
                recommendedSecretSymbol = "قوس و قزح (Rainbow/Arch)",
                recommendedSeal = "مہر محبت (Seal of Love)",
                difficultyLevel = "Hard",
                specialNotes = "Must have pure intentions. Harām relationships will backfire."
            ),
            TalismTemplateEntity(
                category = "Reconciliation",
                purposeDescription = "To resolve conflicts and bring peace between separated or angry parties.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بالصلح بين فلان وفلان وإزالة العداوة",
                alternativeTawkeel = "ألفوا بين قلوب فلان وفلان بالمحبة والسلام",
                suitableQuranicVerse = "وَالصُّلْحُ خَيْرٌ",
                suitableDivineName = "يا سلام يا مؤمن",
                alternativeDivineName = "يا ولي يا حليم",
                suitablePlanet = "مشتری (Jupiter)",
                suitableElement = "ہوائی (Air)",
                suitableZodiac = "جوزا (Gemini)",
                suitableDay = "جمعرات (Thursday)",
                suitableHour = "مشتری (Jupiter Hour)",
                suitableInkColor = "سبز یا زعفران (Green or Saffron)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "جاوی، صندل (Benzoin, Sandalwood)",
                writingDirection = "شمال (North)",
                recommendedShape = "مثلث (Triangle) / مربع (Square)",
                recommendedBorderStyle = "لائن مستقیم (Straight Line)",
                recommendedSecretSymbol = "ترازہ (Scales)",
                recommendedSeal = "خاتم مشتری (Seal of Jupiter)",
                difficultyLevel = "Medium",
                specialNotes = "Recommended to be placed in a high place or worn by the seeker."
            ),
            TalismTemplateEntity(
                category = "Business",
                purposeDescription = "For increasing sales, business growth, and attracting customers.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بجلب الزبائن والرزق إلى هذا المكان",
                alternativeTawkeel = "افتحوا أبواب الرزق والبركة في تجارة فلان",
                suitableQuranicVerse = "يَرْجُونَ تِجَارَةً لَّن تَبُورَ",
                suitableDivineName = "يا رزاق يا فتاح",
                alternativeDivineName = "يا غني يا مغني",
                suitablePlanet = "عطارد (Mercury)",
                suitableElement = "خاکی (Earth)",
                suitableZodiac = "سنبلہ (Virgo)",
                suitableDay = "بدھ (Wednesday)",
                suitableHour = "عطارد (Mercury Hour)",
                suitableInkColor = "سبز یا نیلا (Green or Blue)",
                suitablePaperColor = "زرد (Yellow)",
                suitableBakhoor = "مستکی، لبان (Mastic, Frankincense)",
                writingDirection = "مشرق (East)",
                recommendedShape = "مخمس (Pentagon) / مربع (Square)",
                recommendedBorderStyle = "دوہری لائن (Double Line)",
                recommendedSecretSymbol = "درہم / سکہ (Coin symbol)",
                recommendedSeal = "خاتم عطارد (Seal of Mercury)",
                difficultyLevel = "Easy",
                specialNotes = "Hang it in the shop or place of business where money is kept."
            ),
            TalismTemplateEntity(
                category = "Job",
                purposeDescription = "To secure employment, pass an interview, or get a promotion.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بتيسير حصول فلان على الوظيفة أو العمل",
                alternativeTawkeel = "سخروا قلوب أصحاب العمل لقبول فلان بن فلانة",
                suitableQuranicVerse = "إِنَّ خَيْرَ مَنِ اسْتَأْجَرْتَ الْقَوِيُّ الْأَمِينُ",
                suitableDivineName = "يا باسط يا رافع",
                alternativeDivineName = "يا معز يا وهاب",
                suitablePlanet = "شمس (Sun)",
                suitableElement = "آتشی (Fire)",
                suitableZodiac = "اسد (Leo)",
                suitableDay = "اتوار (Sunday)",
                suitableHour = "شمس (Sun Hour)",
                suitableInkColor = "سنہری یا پیلا (Gold or Yellow)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "عنبر، زعفران (Amber, Saffron)",
                writingDirection = "مشرق (East)",
                recommendedShape = "مسدس (Hexagon)",
                recommendedBorderStyle = "شعاعی (Radiant)",
                recommendedSecretSymbol = "تاج (Crown)",
                recommendedSeal = "خاتم شمس (Seal of the Sun)",
                difficultyLevel = "Medium",
                specialNotes = "Keep with you during interviews."
            ),
            TalismTemplateEntity(
                category = "Money",
                purposeDescription = "To attract wealth, pay off debts, and increase financial abundance.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بجلب المال وسداد دين فلان بن فلانة",
                alternativeTawkeel = "افتحوا خزائن الرزق لفلان بن فلانة",
                suitableQuranicVerse = "وَاللَّهُ يَرْزُقُ مَن يَشَاءُ بِغَيْرِ حِسَابٍ",
                suitableDivineName = "يا غني يا مغني يا رزاق",
                alternativeDivineName = "يا كريم يا معطي",
                suitablePlanet = "مشتری (Jupiter)",
                suitableElement = "خاکی (Earth)",
                suitableZodiac = "قوس (Sagittarius)",
                suitableDay = "جمعرات (Thursday)",
                suitableHour = "مشتری (Jupiter Hour)",
                suitableInkColor = "زرد یا سبز (Yellow or Green)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "عود، صندل سرخ (Oud, Red Sandalwood)",
                writingDirection = "شمال (North)",
                recommendedShape = "مربع (Square)",
                recommendedBorderStyle = "بند مربع (Closed Square)",
                recommendedSecretSymbol = "خزانہ (Treasure/Box)",
                recommendedSeal = "خاتم مشتری (Seal of Jupiter)",
                difficultyLevel = "Medium",
                specialNotes = "Keep in wallet or safe."
            ),
            TalismTemplateEntity(
                category = "Protection",
                purposeDescription = "For protection against evil eye, black magic, jinn, and physical harm.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بحفظ فلان بن فلانة من كل سوء وشر",
                alternativeTawkeel = "احرسوا حامل هذا الحجاب من شر الجن والإنس",
                suitableQuranicVerse = "فَاللَّهُ خَيْرٌ حَافِظًا وَهُوَ أَرْحَمُ الرَّاحِمِينَ",
                suitableDivineName = "يا حفيظ يا رقيب",
                alternativeDivineName = "يا مانع يا جبار",
                suitablePlanet = "زحل (Saturn)",
                suitableElement = "خاکی (Earth)",
                suitableZodiac = "جدی (Capricorn)",
                suitableDay = "ہفتہ (Saturday)",
                suitableHour = "زحل (Saturn Hour)",
                suitableInkColor = "سیاہ (Black) / نیلا (Blue)",
                suitablePaperColor = "نیلا یا سفید (Blue or White)",
                suitableBakhoor = "حرمل، لوبان (Rue, Frankincense)",
                writingDirection = "جنوب (South)",
                recommendedShape = "دائرہ (Circle) / مسدس (Hexagon)",
                recommendedBorderStyle = "موٹی لائن (Thick bold line)",
                recommendedSecretSymbol = "تلوار یا ڈھال (Sword or Shield)",
                recommendedSeal = "خاتم زحل (Seal of Saturn)",
                difficultyLevel = "Easy",
                specialNotes = "Should be worn on the right arm or neck."
            ),
            TalismTemplateEntity(
                category = "Enemy",
                purposeDescription = "To subdue enemies, stop gossip, and protect from oppressors.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بعقد لسان فلان بن فلانة وقهره",
                alternativeTawkeel = "ردوا كيد الظالم في نحره",
                suitableQuranicVerse = "إِنَّا كَفَيْنَاكَ الْمُسْتَهْزِئِينَ",
                suitableDivineName = "يا قهار يا منتقم",
                alternativeDivineName = "يا مذل يا قابض",
                suitablePlanet = "مریخ (Mars)",
                suitableElement = "آتشی (Fire)",
                suitableZodiac = "عقرب (Scorpio)",
                suitableDay = "منگل (Tuesday)",
                suitableHour = "مریخ (Mars Hour)",
                suitableInkColor = "سرخ (Red)",
                suitablePaperColor = "سرخ یا خاکی (Red or Khaki)",
                suitableBakhoor = "لہسن، پیاز کے چھلکے، رائی (Garlic, Onion peels, Mustard seeds)",
                writingDirection = "جنوب (South)",
                recommendedShape = "مثلث (Triangle)",
                recommendedBorderStyle = "نوکیلی لائن (Spiked Line)",
                recommendedSecretSymbol = "تلوار (Sword)",
                recommendedSeal = "خاتم مریخ (Seal of Mars)",
                difficultyLevel = "Hard",
                specialNotes = "Never use unjustly, severe karma will follow."
            ),
            TalismTemplateEntity(
                category = "Court",
                purposeDescription = "To win a court case, get justice, or gain favor of the judge.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بتسخير القاضي لفلان بن فلانة ونصره في قضيته",
                alternativeTawkeel = "انصروا فلان على من ظلمه في هذه المحكمة",
                suitableQuranicVerse = "وَقُلْ جَاءَ الْحَقُّ وَزَهَقَ الْبَاطِلُ",
                suitableDivineName = "يا عدل يا فتاح يا ناصر",
                alternativeDivineName = "يا مقسط يا حكم",
                suitablePlanet = "مشتری (Jupiter) / شمس (Sun)",
                suitableElement = "ہوائی (Air)",
                suitableZodiac = "میزان (Libra)",
                suitableDay = "جمعرات (Thursday) / اتوار (Sunday)",
                suitableHour = "مشتری (Jupiter Hour)",
                suitableInkColor = "سبز یا زعفران (Green or Saffron)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "صندل، عود (Sandalwood, Oud)",
                writingDirection = "مشرق (East)",
                recommendedShape = "ہشت پہلو (Octagon)",
                recommendedBorderStyle = "زنجیری (Chain link)",
                recommendedSecretSymbol = "ترازو (Scales)",
                recommendedSeal = "خاتم مشتری (Seal of Jupiter)",
                difficultyLevel = "Medium",
                specialNotes = "Must be carried on the day of the hearing."
            ),
            TalismTemplateEntity(
                category = "Health",
                purposeDescription = "For healing from diseases, both physical and spiritual.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بشفاء فلان بن فلانة من كل داء ومرض",
                alternativeTawkeel = "ارفعوا البلاء والعلة عن جسد فلان",
                suitableQuranicVerse = "وَإِذَا مَرِضْتُ فَهُوَ يَشْفِينِ",
                suitableDivineName = "يا شافي يا معافي",
                alternativeDivineName = "يا محيي يا سلام",
                suitablePlanet = "شمس (Sun)",
                suitableElement = "آبی (Water)",
                suitableZodiac = "حوت (Pisces)",
                suitableDay = "اتوار (Sunday)",
                suitableHour = "شمس (Sun Hour)",
                suitableInkColor = "زعفران و گلاب (Saffron and Rose water)",
                suitablePaperColor = "سفید چینی کی پلیٹ (White ceramic plate)",
                suitableBakhoor = "کافور، لوبان (Camphor, Frankincense)",
                writingDirection = "مشرق (East)",
                recommendedShape = "مربع (Square)",
                recommendedBorderStyle = "بغیر لائن (No borders, wash and drink)",
                recommendedSecretSymbol = "آب (Drop of water)",
                recommendedSeal = "خاتم شمس (Seal of Sun)",
                difficultyLevel = "Easy",
                specialNotes = "Often written on a plate and washed with water to drink."
            ),
            TalismTemplateEntity(
                category = "Children",
                purposeDescription = "For conceiving a child, protecting pregnancy, or general well-being of offspring.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بحفظ جنين فلانة أو رزقها بالذرية الصالحة",
                alternativeTawkeel = "ارزقوا فلانة بن فلانة ذرية طيبة عاجلا غير آجل",
                suitableQuranicVerse = "رَبِّ لَا تَذَرْنِي فَرْدًا وَأَنتَ خَيْرُ الْوَارِثِينَ",
                suitableDivineName = "يا مصور يا خالق يا بارئ",
                alternativeDivineName = "يا وهاب يا معطي",
                suitablePlanet = "قمر (Moon)",
                suitableElement = "آبی (Water)",
                suitableZodiac = "سرطان (Cancer)",
                suitableDay = "پیر (Monday)",
                suitableHour = "قمر (Moon Hour)",
                suitableInkColor = "سبز یا نیلا (Green or Blue)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "کافور، صندل سفید (Camphor, White Sandalwood)",
                writingDirection = "مغرب (West)",
                recommendedShape = "مربع (Square) / دائرہ (Circle)",
                recommendedBorderStyle = "لائن نورانی (Luminous Line)",
                recommendedSecretSymbol = "پھول (Flower)",
                recommendedSeal = "خاتم قمر (Seal of Moon)",
                difficultyLevel = "Medium",
                specialNotes = "To be worn around the waist by the woman."
            ),
            TalismTemplateEntity(
                category = "Travel",
                purposeDescription = "For safe journey, easy visa process, and successful relocation.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بتيسير سفر فلان بن فلانة وحفظه في طريقه",
                alternativeTawkeel = "سهلوا لفلان أسباب السفر والرحيل",
                suitableQuranicVerse = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَٰذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ",
                suitableDivineName = "يا حافظ يا فتاح",
                alternativeDivineName = "يا وكيل يا مجيب",
                suitablePlanet = "قمر (Moon)",
                suitableElement = "ہوائی (Air)",
                suitableZodiac = "جوزا (Gemini) / قوس (Sagittarius)",
                suitableDay = "پیر (Monday)",
                suitableHour = "قمر (Moon Hour)",
                suitableInkColor = "زعفران (Saffron)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "عود (Oud)",
                writingDirection = "شمال (North)",
                recommendedShape = "دائرہ (Circle)",
                recommendedBorderStyle = "متحرک لائن (Dashed/Moving Line)",
                recommendedSecretSymbol = "پرندہ (Bird/Wings)",
                recommendedSeal = "خاتم قمر (Seal of Moon)",
                difficultyLevel = "Easy",
                specialNotes = "Keep in passport or travel bag."
            ),
            TalismTemplateEntity(
                category = "Success",
                purposeDescription = "For success in exams, tests, education, and general endeavors.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بنجاح فلان بن فلانة وتفتيح عقله للحفظ والفهم",
                alternativeTawkeel = "افتحوا بصيرة فلان وسهلوا اختباره",
                suitableQuranicVerse = "رَبِّ اشْرَحْ لِي صَدْرِي وَيَسِّرْ لِي أَمْرِي",
                suitableDivineName = "يا عليم يا حكيم يا فتاح",
                alternativeDivineName = "يا خبير يا مبين",
                suitablePlanet = "عطارد (Mercury)",
                suitableElement = "ہوائی (Air)",
                suitableZodiac = "جوزا (Gemini)",
                suitableDay = "بدھ (Wednesday)",
                suitableHour = "عطارد (Mercury Hour)",
                suitableInkColor = "زرد یا سبز (Yellow or Green)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "مصطکی (Mastic Gum)",
                writingDirection = "مشرق (East)",
                recommendedShape = "مثلث (Triangle) / مربع (Square)",
                recommendedBorderStyle = "نقطہ دار لائن (Dotted Line)",
                recommendedSecretSymbol = "کتاب یا قلم (Book or Pen)",
                recommendedSeal = "خاتم عطارد (Seal of Mercury)",
                difficultyLevel = "Easy",
                specialNotes = "Student can wear it or drink its water before exam."
            ),
            TalismTemplateEntity(
                category = "Victory",
                purposeDescription = "To overcome competitors, win competitions, and achieve dominance.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بغلبة فلان بن فلانة على خصومه وقهره لهم",
                alternativeTawkeel = "انصروا فلان نصراً عزيزاً مؤزراً",
                suitableQuranicVerse = "إِنَّا فَتَحْنَا لَكَ فَتْحًا مُّبِينًا",
                suitableDivineName = "يا عزيز يا جبار يا متكبر",
                alternativeDivineName = "يا قهار يا قوي",
                suitablePlanet = "مریخ (Mars) / شمس (Sun)",
                suitableElement = "آتشی (Fire)",
                suitableZodiac = "حمل (Aries)",
                suitableDay = "منگل (Tuesday)",
                suitableHour = "مریخ (Mars Hour)",
                suitableInkColor = "سرخ (Red)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "صندل سرخ (Red Sandalwood)",
                writingDirection = "جنوب (South)",
                recommendedShape = "مثلث (Triangle)",
                recommendedBorderStyle = "دوہری لائن (Double Line)",
                recommendedSecretSymbol = "تلوار یا تیر (Sword or Arrow)",
                recommendedSeal = "خاتم مریخ (Seal of Mars)",
                difficultyLevel = "Hard",
                specialNotes = "Requires jalali abstinences."
            ),
            TalismTemplateEntity(
                category = "General Purpose",
                purposeDescription = "For any valid purpose not covered in other categories.",
                arabicTawkeel = "توكلوا يا خدام هذا الطلسم بقضاء حاجة فلان بن فلانة وهي كذا وكذا",
                alternativeTawkeel = "يا خدام هذا العمل اقضوا حاجتي",
                suitableQuranicVerse = "وَقَالَ رَبُّكُمُ ادْعُونِي أَسْتَجِبْ لَكُمْ",
                suitableDivineName = "يا مجيب يا وهاب",
                alternativeDivineName = "يا سميع يا بصير",
                suitablePlanet = "مشتری (Jupiter)",
                suitableElement = "خاکی (Earth)",
                suitableZodiac = "حمل (Aries)",
                suitableDay = "جمعرات (Thursday)",
                suitableHour = "مشتری (Jupiter Hour)",
                suitableInkColor = "زعفران و گلاب (Saffron and Rose)",
                suitablePaperColor = "سفید (White)",
                suitableBakhoor = "عود و لوبان (Oud and Frankincense)",
                writingDirection = "مشرق (East)",
                recommendedShape = "مربع (Square)",
                recommendedBorderStyle = "سادہ لائن (Simple Line)",
                recommendedSecretSymbol = "ستارہ (Star)",
                recommendedSeal = "خاتم سلیمانی (Seal of Solomon)",
                difficultyLevel = "General",
                specialNotes = "Ensure the purpose is pure and lawful."
            )
        )
    }
}
