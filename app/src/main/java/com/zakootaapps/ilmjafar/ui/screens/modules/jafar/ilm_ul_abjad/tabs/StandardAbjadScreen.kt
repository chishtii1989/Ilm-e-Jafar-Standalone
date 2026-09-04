package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services.AbjadCalculatorService
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services.AbjadLookupService
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services.NuqaatService
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services.MaratebService
import com.zakootaapps.ilmjafar.ui.theme.AppUrduFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable 
fun StandardAbjadScreen() { 
    var inputText by remember { mutableStateOf("") }
    
    val inputAsNumber = inputText.trim().toIntOrNull()
    val isNumberLookup = inputAsNumber != null

    val calculationResults = remember(inputText, isNumberLookup) { 
        if (isNumberLookup) emptyList() else AbjadCalculatorService.calculate(inputText) 
    }
    val totalAbjad = calculationResults.sumOf { it.value }

    val nuqaatResults = remember(inputText, isNumberLookup) {
        if (isNumberLookup) emptyList() else NuqaatService.calculate(inputText)
    }
    val totalNuqaat = nuqaatResults.sumOf { it.count }

    val maratebResults = remember(inputText, isNumberLookup) {
        if (isNumberLookup) emptyList() else MaratebService.calculate(inputText)
    }
    val processedLetters = maratebResults.size

    val lookupResult = remember(inputAsNumber) {
        if (inputAsNumber != null) {
            AbjadLookupService.getLetterForNumber(inputAsNumber)
        } else {
            ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text or number", fontFamily = AppUrduFontFamily) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(fontFamily = AppUrduFontFamily, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isNumberLookup) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = lookupResult,
                        fontFamily = AppUrduFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        } else if (calculationResults.isNotEmpty() || nuqaatResults.isNotEmpty() || maratebResults.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (calculationResults.isNotEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Total Abjad = $totalAbjad",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Total Letters = ${calculationResults.size}",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    calculationResults.forEach { result ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = result.letter.toString(),
                                                fontFamily = AppUrduFontFamily,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(text = "=", fontSize = 20.sp)
                                            Text(
                                                text = result.value.toString(),
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (nuqaatResults.isNotEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Nuqaat Details",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Total Nuqaat = $totalNuqaat",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    nuqaatResults.forEach { result ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = result.letter.toString(),
                                                fontFamily = AppUrduFontFamily,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(text = "→", fontSize = 20.sp)
                                            Text(
                                                text = result.count.toString(),
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }

                if (maratebResults.isNotEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Marateb Details",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Processed Letters = $processedLetters",
                                    fontFamily = AppUrduFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    maratebResults.forEach { result ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = result.letter.toString(),
                                                fontFamily = AppUrduFontFamily,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(text = "→", fontSize = 20.sp)
                                            Text(
                                                text = "Maratib ${result.marateb}",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
