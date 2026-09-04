package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.muwafiqat_adad

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.zakootaapps.ilmjafar.ui.theme.AppUrduFontFamily
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.JafarPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuwafiqatAdadScreen(navController: NavController, title: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, fontFamily = AppUrduFontFamily) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(paddingValues)) {
            JafarPlaceholder()
        }
    }
}
