package com.zakootaapps.ilmjafar.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.JafarHomeScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.asma_ul_husna.AsmaUlHusnaScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.bast_qabz.BastQabzScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.falnama.FalnamaScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ghalib_maghloob.GhalibMaghloobScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.IlmUlAbjadScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_adad.IlmUlAdadScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ism_e_azam.IsmEAzamScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.istikhraj_ism.IstikhrajIsmScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.jafar.MasterJafarScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.muwafiqat_adad.MuwafiqatAdadScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.muwafiqat_huroof.MuwafiqatHuroofScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.qalb_aks.QalbAksScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.rohani_hisabat.RohaniHisabatScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.rohani_tashkhees.RohaniTashkheesScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.takseer_basti.TakseerBastiScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.takseer_huroof.TakseerHuroofScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.takseer_musawitat.TakseerMusawitatScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.takseer_sawamit.TakseerSawamitScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "jafar_home", modifier = Modifier.fillMaxSize()) {
        composable("jafar_home") { JafarHomeScreen(navController) }
        composable("jafar_master") { MasterJafarScreen(navController) }
        composable("jafar_ilm_ul_abjad") { IlmUlAbjadScreen(navController) }
        composable("jafar_ilm_ul_adad") { IlmUlAdadScreen(navController, "علم الاعداد") }
        composable("jafar_ism_e_azam") { IsmEAzamScreen(navController, "اسم اعظم") }
        composable("jafar_istikhraj_ism") { IstikhrajIsmScreen(navController, "استخراج اسم") }
        composable("jafar_asma_ul_husna") { AsmaUlHusnaScreen(navController, "اسماء الحسنی") }
        composable("jafar_ghalib_maghloob") { GhalibMaghloobScreen(navController, "غالب و مغلوب") }
        composable("jafar_rohani_tashkhees") { RohaniTashkheesScreen(navController, "روحانی تشخیص") }
        composable("jafar_rohani_hisabat") { RohaniHisabatScreen(navController, "روحانی حسابات") }
        composable("jafar_muwafiqat_adad") { MuwafiqatAdadScreen(navController, "موافقات اعداد") }
        composable("jafar_muwafiqat_huroof") { MuwafiqatHuroofScreen(navController, "موافقات حروف") }
        composable("jafar_qalb_aks") { QalbAksScreen(navController, "قلب و عکس") }
        composable("jafar_takseer_basti") { TakseerBastiScreen(navController, "تکسیر بسطی") }
        composable("jafar_takseer_huroof") { TakseerHuroofScreen(navController, "تکسیر حروف") }
        composable("jafar_takseer_musawitat") { TakseerMusawitatScreen(navController, "تکسیر متوسطات") }
        composable("jafar_takseer_sawamit") { TakseerSawamitScreen(navController, "تکسیر صوامت") }
        composable("jafar_bast_qabz") { BastQabzScreen(navController, "بسط و قبض") }
        composable("jafar_falnama") { FalnamaScreen(navController, "فالنامہ") }
    }
}
