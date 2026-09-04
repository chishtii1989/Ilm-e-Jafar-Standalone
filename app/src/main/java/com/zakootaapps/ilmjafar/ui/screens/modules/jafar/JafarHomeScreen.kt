package com.zakootaapps.ilmjafar.ui.screens.modules.jafar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zakootaapps.ilmjafar.ui.theme.AppUrduFontFamily

private data class JafarSection(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JafarHomeScreen(navController: NavController) {
    val sections = listOf(
        JafarSection("جفر", "jafar_master", Icons.Default.GridView),
        JafarSection("علم الابجد", "jafar_ilm_ul_abjad", Icons.Default.Calculate),
        JafarSection("علم الاعداد", "jafar_ilm_ul_adad", Icons.Default.Calculate),
        JafarSection("اسم اعظم", "jafar_ism_e_azam", Icons.Default.AutoAwesome),
        JafarSection("استخراج اسم", "jafar_istikhraj_ism", Icons.Default.AutoAwesome),
        JafarSection("اسماء الحسنی", "jafar_asma_ul_husna", Icons.Default.AutoAwesome),
        JafarSection("غالب و مغلوب", "jafar_ghalib_maghloob", Icons.Default.Calculate),
        JafarSection("روحانی تشخیص", "jafar_rohani_tashkhees", Icons.Default.Info),
        JafarSection("روحانی حسابات", "jafar_rohani_hisabat", Icons.Default.Calculate),
        JafarSection("موافقات اعداد", "jafar_muwafiqat_adad", Icons.Default.Calculate),
        JafarSection("موافقات حروف", "jafar_muwafiqat_huroof", Icons.Default.Calculate),
        JafarSection("قلب و عکس", "jafar_qalb_aks", Icons.Default.GridView),
        JafarSection("تکسیر بسطی", "jafar_takseer_basti", Icons.Default.GridView),
        JafarSection("تکسیر حروف", "jafar_takseer_huroof", Icons.Default.GridView),
        JafarSection("تکسیر متوسطات", "jafar_takseer_musawitat", Icons.Default.GridView),
        JafarSection("تکسیر صوامت", "jafar_takseer_sawamit", Icons.Default.GridView),
        JafarSection("بسط و قبض", "jafar_bast_qabz", Icons.Default.Calculate),
        JafarSection("فالنامہ", "jafar_falnama", Icons.Default.AutoAwesome)
    )
    Scaffold(topBar = { TopAppBar(title = { Text("علمِ جفر", fontFamily = AppUrduFontFamily) }) }) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sections) { section ->
                Card(onClick = { navController.navigate(section.route) }) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(section.icon, contentDescription = section.title)
                        Text(section.title, fontFamily = AppUrduFontFamily, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}
