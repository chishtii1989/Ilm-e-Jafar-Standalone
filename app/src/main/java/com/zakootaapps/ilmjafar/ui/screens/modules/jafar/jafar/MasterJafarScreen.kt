package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.jafar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zakootaapps.ilmjafar.ui.theme.AppUrduFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterJafarScreen(navController: NavController) {
    val tabs = listOf("جفر صغیر", "جفر جامع", "جدول جفر", "جفر کبیر")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("جفر", fontFamily = AppUrduFontFamily) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 8.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, fontFamily = AppUrduFontFamily) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> JafarSaghirScreen()
                1 -> JafarJamiaScreen()
                2 -> JadwalJafarScreen()
                3 -> JafarKabirScreen()
            }
        }
    }
}
