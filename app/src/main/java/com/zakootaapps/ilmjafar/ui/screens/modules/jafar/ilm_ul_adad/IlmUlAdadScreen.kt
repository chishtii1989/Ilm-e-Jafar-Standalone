package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_adad

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zakootaapps.ilmjafar.data.local.ModularDatabase
import com.zakootaapps.ilmjafar.data.repository.ilmuladad_knowledge.IlmUlAdadKnowledgeRepository
import com.zakootaapps.ilmjafar.data.repository.modules.professional_ilmuladad.ProfessionalIlmUlAdadHistoryRepository
import com.zakootaapps.ilmjafar.domain.integration.orchestrator.EngineProvider
import com.zakootaapps.ilmjafar.ui.screens.modules.professional_ilmuladad.ProfessionalIlmUlAdadScreen
import com.zakootaapps.ilmjafar.ui.screens.modules.professional_ilmuladad.ProfessionalIlmUlAdadViewModel

@Composable
fun IlmUlAdadScreen(navController: NavController, title: String) {
    val context = LocalContext.current
    val db = ModularDatabase.getDatabase(context)
    val historyRepo = ProfessionalIlmUlAdadHistoryRepository(db.professionalIlmUlAdadHistoryDao())
    val knowledgeRepo = IlmUlAdadKnowledgeRepository(db.ilmuladadKnowledgeDao())
    val orchestrator = EngineProvider.getOrchestrator(context)
    
    val viewModel = viewModel<ProfessionalIlmUlAdadViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val app = context.applicationContext as Application
                return ProfessionalIlmUlAdadViewModel(app, orchestrator, historyRepo, knowledgeRepo) as T
            }
        }
    )
    
    ProfessionalIlmUlAdadScreen(
        viewModel = viewModel,
        onBack = { navController.navigateUp() }
    )
}
