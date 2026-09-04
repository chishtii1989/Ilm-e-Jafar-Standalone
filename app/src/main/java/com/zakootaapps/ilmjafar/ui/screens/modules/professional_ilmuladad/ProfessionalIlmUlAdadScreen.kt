package com.zakootaapps.ilmjafar.ui.screens.modules.professional_ilmuladad

import com.zakootaapps.ilmjafar.domain.adad.engine.dob.AdadDobPdfGenerator
import kotlinx.coroutines.launch


import android.widget.Toast
import android.app.DatePickerDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.unit.sp
import com.zakootaapps.ilmjafar.data.local.modules.professional_ilmuladad.ProfessionalIlmUlAdadHistoryEntity
import com.zakootaapps.ilmjafar.domain.adad.engine.IlmUlAdadResult
import com.zakootaapps.ilmjafar.ui.theme.AppUrduFontFamily
import java.util.Calendar

@Composable
fun ProfessionalIlmUlAdadScreen(
    viewModel: ProfessionalIlmUlAdadViewModel,
    onBack: () -> Unit
) {
    var currentScreen by remember { mutableStateOf("ENTRY") }
    
    LaunchedEffect(currentScreen) {
        if (currentScreen == "ENTRY") {
            viewModel.clearResult()
        }
    }

    when (currentScreen) {
        "ENTRY" -> {
            EntryScreen(
                onAstrologyClick = { 
                    viewModel.updateMethod("DOB")
                    currentScreen = "ASTROLOGY" 
                },
                onNumerologyClick = { 
                    viewModel.updateMethod("NAME")
                    currentScreen = "NUMEROLOGY" 
                },
                onBack = onBack
            )
        }
        "ASTROLOGY" -> {
            AstrologyScreen(viewModel = viewModel, onBack = { currentScreen = "ENTRY" })
        }
        "NUMEROLOGY" -> {
            NumerologyScreen(viewModel = viewModel, onBack = { currentScreen = "ENTRY" })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(
    onAstrologyClick: () -> Unit,
    onNumerologyClick: () -> Unit,
    onBack: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("ماسٹر رپورٹ", fontFamily = AppUrduFontFamily, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = Color(0xFF1B5E20),
                        navigationIconContentColor = Color(0xFF1B5E20)
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "طریقہ کار منتخب کریں",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = AppUrduFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = onAstrologyClick,
                    modifier = Modifier.fillMaxWidth().height(64.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20), contentColor = Color.White)
                ) {
                    Text("تاریخ پیدائش", fontFamily = AppUrduFontFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = onNumerologyClick,
                    modifier = Modifier.fillMaxWidth().height(64.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20), contentColor = Color.White)
                ) {
                    Text("نام اور والدہ کا نام (Numerology)", fontFamily = AppUrduFontFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ASTROLOGY SCREEN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstrologyScreen(
    viewModel: ProfessionalIlmUlAdadViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            viewModel.updateDob("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("علم الاعداد بذریعہ تاریخ پیدائش", fontFamily = AppUrduFontFamily, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                            uiState.result?.dobNumerology?.let { num ->
                                val coroutineScope = rememberCoroutineScope()
                                IconButton(onClick = {
                                    coroutineScope.launch {
                                        AdadDobPdfGenerator.generateAndSharePdf(context, num, uiState.dob)
                                    }
                                }) {
                                    Icon(Icons.Default.Share, contentDescription = "Share PDF", tint = Color(0xFF1B5E20))
                                }
                            }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = Color(0xFF1B5E20),
                        navigationIconContentColor = Color(0xFF1B5E20)
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = uiState.dob,
                        onValueChange = { viewModel.updateDob(it) },
                        label = { Text("تاریخ پیدائش (ضروری)", fontFamily = AppUrduFontFamily) },
                        modifier = Modifier.fillMaxWidth().clickable { datePickerDialog.show() },
                        enabled = false, 
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        trailingIcon = { 
                            IconButton(onClick = { datePickerDialog.show() }) {
                                Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                
                item {
                    Button(
                        onClick = { viewModel.calculate() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                        } else {
                            Text("رپورٹ تیار کریں", fontFamily = AppUrduFontFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                if (uiState.error != null) {
                    item {
                        Text(
                            text = uiState.error ?: "",
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = AppUrduFontFamily,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                uiState.result?.dobNumerology?.let { num ->
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    "اعداد کی تفصیل (Numbers)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20)
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        ReportBadge("مفرد عدد (Birth Number)", num.birthNumber.toString())
                                        ReportBadge("عنوان (Title)", num.title)
                                    }
                                }
                            }
                        }
                    }
                    
                    item { ExpandableSection("شخصیت\nPersonality", num.personality, Icons.Default.Person) }
                    item { ExpandableSection("ذہنی فطرت (Mental Nature)", num.mentalNature, Icons.Default.Face) }
                    item { ExpandableSection("جذباتی فطرت (Emotional Nature)", num.emotionalNature, Icons.Default.Favorite) }
                    item { ExpandableSection("فیصلہ سازی (Decision Making)", num.decisionMaking, Icons.Default.CheckCircle) }
                    item { ExpandableSection("قائدانہ صلاحیت (Leadership Style)", num.leadershipStyle, Icons.Default.Star) }
                    item { ExpandableSection("خوبیاں (Strengths)", num.topStrengths, Icons.Default.ThumbUp) }
                    item { ExpandableSection("خامیاں (Weaknesses)", num.topWeaknesses, Icons.Default.Warning) }
                    item { ExpandableSection("عام غلطیاں (Common Mistakes)", num.commonMistakes, Icons.Default.Warning) }
                    item { ExpandableSection("پوشیدہ صلاحیتیں (Hidden Talents)", num.hiddenTalents, Icons.Default.Star) }
                    item { ExpandableSection("سیکھنے کا انداز (Learning Style)", num.learningStyle, Icons.Default.Info) }
                    item { ExpandableSection("بات چیت کا انداز (Communication)", num.communicationStyle, Icons.Default.Chat) }
                    item { ExpandableSection("موزوں کیریئر (Suitable Careers)", num.careerSuitable, Icons.Default.Work) }
                    item { ExpandableSection("ناموزوں کیریئر (Unsuitable Careers)", num.careerUnsuitable, Icons.Default.Close) }
                    item { ExpandableSection("کاروبار (Business Profile)", num.businessProfile, Icons.Default.Business) }
                    item { ExpandableSection("نوکری (Job Profile)", num.jobProfile, Icons.Default.Work) }
                    item { ExpandableSection("مالی رویہ (Financial Behaviour)", num.financialBehaviour, Icons.Default.MonetizationOn) }
                    item { ExpandableSection("سرمایہ کاری (Investment Behaviour)", num.investmentBehaviour, Icons.Default.AccountBalance) }
                    item { ExpandableSection("شادی\nMarriage", num.marriage, Icons.Default.Favorite) }
                    item { ExpandableSection("تعلقات (Relationships)", num.relationshipStyle, Icons.Default.FavoriteBorder) }
                    item { ExpandableSection("خاندانی زندگی (Family Life)", num.familyLife, Icons.Default.Home) }
                    item { ExpandableSection("دوستی (Friendship)", num.friendship, Icons.Default.Person) }
                    item { ExpandableSection("بچے (Children)", num.children, Icons.Default.Face) }
                    item { ExpandableSection("صحت (Health Tendencies)", num.healthTendencies, Icons.Default.Favorite) }
                    item { ExpandableSection("حفاظتی صحت (Preventive Health)", num.preventiveHealthAdvice, Icons.Default.Info) }
                    item { ExpandableSection("خوراک (Food Advice)", num.foodAdvice, Icons.Default.ShoppingCart) }
                    item { ExpandableSection("ورزش (Exercise Advice)", num.exerciseAdvice, Icons.Default.DirectionsRun) }
                    item { ExpandableSection("ذہنی دباؤ (Stress Management)", num.stressManagement, Icons.Default.Face) }
                    item { ExpandableSection("نیند (Sleep Advice)", num.sleepAdvice, Icons.Default.Face) }
                    item { ExpandableSection("روحانیت (Spiritual Nature)", num.spiritualNature, Icons.Default.Star) }
                    item { ExpandableSection("خود کی بہتری (Self Improvement)", num.selfImprovement, Icons.Default.Star) }
                    item { ExpandableSection("زندگی کا مقصد (Life Mission)", num.lifeMission, Icons.Default.Star) }
                    item { ExpandableSection("روزمرہ نصیحت (Daily Advice)", num.dailyAdvice, Icons.Default.Info) }
                    item { ExpandableSection("ماہانہ نصیحت (Monthly Advice)", num.monthlyAdvice, Icons.Default.Info) }
                    item { ExpandableSection("طویل مدتی نصیحت (Long Term Advice)", num.longTermAdvice, Icons.Default.Info) }
                    item { ExpandableSection("خلاصہ (Professional Summary)", num.professionalSummary, Icons.Default.CheckCircle) }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text(
                                    "موافق اور غیر موافق (Lucky & Unlucky)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20)
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("سیارہ (Planet)", num.planet)
                                    ReportBadge("عنصر (Element)", num.element)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق رنگ (Lucky Colours)", num.luckyColours)
                                    ReportBadge("ناموافق رنگ (Avoid Colours)", num.avoidColours)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق دن (Lucky Days)", num.luckyDays)
                                    ReportBadge("موافق سمتیں (Lucky Directions)", num.luckyDirections)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق نمبر (Lucky Numbers)", num.luckyNumbers)
                                    ReportBadge("ناموافق نمبر (Avoid Numbers)", num.avoidNumbers)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق افراد (Compatible)", num.compatibleNumbers)
                                    ReportBadge("ناموافق افراد (Less Compatible)", num.lessCompatibleNumbers)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// NUMEROLOGY SCREEN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumerologyScreen(
    viewModel: ProfessionalIlmUlAdadViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("علم الاعداد (Numerology)", fontFamily = AppUrduFontFamily, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = Color(0xFF1B5E20),
                        navigationIconContentColor = Color(0xFF1B5E20)
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = uiState.name,
                        onValueChange = { viewModel.updateName(it) },
                        label = { Text("اپنا نام (ضروری)", fontFamily = AppUrduFontFamily) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                item {
                    OutlinedTextField(
                        value = uiState.mothersName,
                        onValueChange = { viewModel.updateMothersName(it) },
                        label = { Text("والدہ کا نام (ضروری)", fontFamily = AppUrduFontFamily) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                
                item {
                    Button(
                        onClick = { viewModel.calculate() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                        } else {
                            Text("رپورٹ تیار کریں", fontFamily = AppUrduFontFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                if (uiState.error != null) {
                    item {
                        Text(
                            text = uiState.error ?: "",
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = AppUrduFontFamily,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                uiState.result?.numerology?.let { num ->
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    "اعداد کی تفصیل (Numbers)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20)
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        ReportBadge("مفرد عدد (Birth Number)", num.birthNumber.toString())
                                        ReportBadge("قسمت کا عدد (Destiny Number)", num.destinyNumber.toString())
                                    }
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        ReportBadge("روحانی عدد (Soul Number)", num.soulNumber.toString())
                                        ReportBadge("اظہار کا عدد (Expression)", num.expressionNumber.toString())
                                    }
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        ReportBadge("شخصیت عدد (Personality)", num.personalityNumber.toString())
                                        ReportBadge("موافق نمبر (Lucky Numbers)", num.luckyNumbers)
                                    }
                                }
                            }
                        }
                    }
                    
                    item { ExpandableSection("شخصیت\nPersonality", num.personality, Icons.Default.Person) }
                    item { ExpandableSection("خوبیاں (Strengths)", num.strengths, Icons.Default.ThumbUp) }
                    item { ExpandableSection("خامیاں (Weaknesses)", num.weaknesses, Icons.Default.Warning) }
                    item { ExpandableSection("زندگی کا مقصد (Life Purpose)", num.lifePurpose, Icons.Default.Star) }
                    item { ExpandableSection("کیریئر (Career)", num.career, Icons.Default.Work) }
                    item { ExpandableSection("مالیات (Financial Life)", num.finance, Icons.Default.MonetizationOn) }
                    item { ExpandableSection("تعلقات (Relationships)", num.relationships, Icons.Default.FavoriteBorder) }
                    item { ExpandableSection("شادی\nMarriage", num.marriage, Icons.Default.Favorite) }
                    item { ExpandableSection("صحت\nHealth", num.health, Icons.Default.Favorite) }
                    item { ExpandableSection("روحانی رہنمائی (Spiritual Guidance)", num.spiritualGuidance, Icons.Default.Star) }
                    item { ExpandableSection("عملی مشورے (Practical Advice)", num.practicalAdvice, Icons.Default.Info) }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text(
                                    "موافق اور غیر موافق (Lucky & Unlucky)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20)
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق رنگ (Lucky Colors)", num.luckyColors)
                                    ReportBadge("موافق دن (Lucky Days)", num.luckyDays)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("موافق سمتیں (Lucky Directions)", num.luckyDirections)
                                    ReportBadge("موافق نمبر (Lucky Numbers)", num.luckyNumbers)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    ReportBadge("غیر موافق نمبر (Unfavourable)", num.unfavourableNumbers)
                                    ReportBadge("موافق پتھر (Lucky Gemstone)", num.luckyGemstone)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.ReportBadge(label: String, value: Any?) {
    val isEmpty = when (value) {
        is String -> value.isBlank() || value == "[]" || value == "null"
        is List<*> -> value.isEmpty()
        else -> value == null
    }
    if (isEmpty) return
    val displayValue = if (value is List<*>) value.joinToString(", ") else value.toString()
    
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = label,
            fontFamily = AppUrduFontFamily,
            fontSize = 11.sp,
            color = Color(0xFF1B5E20).copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = displayValue,
            fontFamily = AppUrduFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
    }
}

@Composable
fun ExpandableSection(title: String, content: Any?, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    val isEmpty = when (content) {
        is String -> content.isBlank() || content == "[]" || content == "null"
        is List<*> -> content.isEmpty()
        else -> content == null
    }
    if (isEmpty) return

    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFC8E6C9),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = Color(0xFF1B5E20), modifier = Modifier.size(24.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title, 
                    fontFamily = AppUrduFontFamily, 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 18.sp, 
                    color = Color(0xFF1B5E20),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, 
                    contentDescription = "Expand", 
                    tint = Color(0xFF1B5E20)
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    
                    if (content is List<*>) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            content.forEach { item ->
                                Row(verticalAlignment = Alignment.Top) {
                                    Text(
                                        text = "•",
                                        fontFamily = AppUrduFontFamily,
                                        fontSize = 16.sp,
                                        color = Color(0xFF1B5E20),
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                    Text(
                                        text = item.toString(),
                                        fontFamily = AppUrduFontFamily,
                                        fontSize = 16.sp,
                                        lineHeight = 26.sp,
                                        color = Color(0xFF1B5E20)
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            text = content.toString(),
                            fontFamily = AppUrduFontFamily,
                            fontSize = 16.sp,
                            lineHeight = 26.sp,
                            color = Color(0xFF1B5E20)
                        )
                    }
                }
            }
        }
    }
}
