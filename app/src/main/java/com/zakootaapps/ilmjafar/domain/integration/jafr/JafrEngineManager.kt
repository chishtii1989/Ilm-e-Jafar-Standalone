package com.zakootaapps.ilmjafar.domain.integration.jafr

import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadResult
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.takseer.TakseerEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.takseer.TakseerResult
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.bast.BastEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.bast.BastResult
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qabz.QabzEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qabz.QabzResult
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qalb.QalbEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qalb.QalbResult
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.aks.AksEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.aks.AksResult
import com.zakootaapps.ilmjafar.domain.ismazam.engine.IsmAzamEngine
import com.zakootaapps.ilmjafar.domain.ismazam.engine.IsmAzamResult
import com.zakootaapps.ilmjafar.domain.tashkhees.engine.RohaniTashkheesEngine
import com.zakootaapps.ilmjafar.domain.tashkhees.engine.RohaniResult
import com.zakootaapps.ilmjafar.domain.tashkhees.engine.TashkheesInput
import java.util.concurrent.ConcurrentHashMap

enum class EngineType {
    ABJAD, TAKSEER, BAST, QABZ, QALB, AKS, ISM_AZAM, ROHANI_TASHKHEES
}

class ExecutionContext {
    private val results = ConcurrentHashMap<EngineType, Any>()
    private val errors = ConcurrentHashMap<EngineType, String>()

    fun <T> putResult(type: EngineType, result: T) {
        results[type] = result as Any
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getResult(type: EngineType): T? {
        return results[type] as? T
    }

    fun putError(type: EngineType, error: String) {
        errors[type] = error
    }

    fun getErrors(): Map<EngineType, String> = errors.toMap()
    
    fun hasError(type: EngineType): Boolean = errors.containsKey(type)
}

data class SharedExecutionResult(
    val context: ExecutionContext,
    val isSuccess: Boolean,
    val summary: String
)

class EngineRegistry(
    val abjadEngine: AbjadEngine,
    val takseerEngine: TakseerEngine,
    val bastEngine: BastEngine,
    val qabzEngine: QabzEngine,
    val qalbEngine: QalbEngine,
    val aksEngine: AksEngine,
    val ismAzamEngine: IsmAzamEngine,
    val tashkheesEngine: RohaniTashkheesEngine
)

class EngineResolver {
    fun resolveDependencies(targetEngines: List<EngineType>): List<EngineType> {
        val resolved = mutableSetOf<EngineType>()
        
        fun addWithDependencies(engine: EngineType) {
            when (engine) {
                EngineType.ROHANI_TASHKHEES -> {
                    addWithDependencies(EngineType.ABJAD)
                    addWithDependencies(EngineType.TAKSEER)
                    addWithDependencies(EngineType.BAST)
                    addWithDependencies(EngineType.QABZ)
                    addWithDependencies(EngineType.QALB)
                    addWithDependencies(EngineType.AKS)
                    addWithDependencies(EngineType.ISM_AZAM)
                }
                EngineType.ISM_AZAM -> {
                    addWithDependencies(EngineType.ABJAD)
                }
                EngineType.TAKSEER, EngineType.BAST, EngineType.QABZ, EngineType.QALB, EngineType.AKS -> {
                    addWithDependencies(EngineType.ABJAD)
                }
                EngineType.ABJAD -> {
                    // Base dependency
                }
            }
            resolved.add(engine)
        }
        
        targetEngines.forEach { addWithDependencies(it) }
        
        // Return sorted by execution order (approximate topological sort)
        val executionOrder = listOf(
            EngineType.ABJAD,
            EngineType.TAKSEER,
            EngineType.BAST,
            EngineType.QABZ,
            EngineType.QALB,
            EngineType.AKS,
            EngineType.ISM_AZAM,
            EngineType.ROHANI_TASHKHEES
        )
        
        return executionOrder.filter { resolved.contains(it) }
    }
}

class EngineCoordinator(
    private val registry: EngineRegistry,
    private val resolver: EngineResolver
) {
    suspend fun execute(
        enginesToRun: List<EngineType>, 
        textInput: String, 
        systemId: Int,
        secondaryInput: String = "",
        methodCode: String = "HAROOF"
    ): SharedExecutionResult {
        val context = ExecutionContext()
        val executionPlan = resolver.resolveDependencies(enginesToRun)
        
        for (engineType in executionPlan) {
            try {
                when (engineType) {
                    EngineType.ABJAD -> {
                        if (context.getResult<AbjadResult>(EngineType.ABJAD) == null) {
                            val result = registry.abjadEngine.calculationService.performCalculation(textInput, systemId)
                            context.putResult(EngineType.ABJAD, result)
                        }
                    }
                    EngineType.TAKSEER -> {
                        val result = registry.takseerEngine.calculationService.calculate(textInput, systemId, methodCode)
                        context.putResult(EngineType.TAKSEER, result)
                    }
                    EngineType.BAST -> {
                        val result = registry.bastEngine.calculationService.calculate(textInput, systemId, methodCode)
                        context.putResult(EngineType.BAST, result)
                    }
                    EngineType.QABZ -> {
                        val result = registry.qabzEngine.calculationService.calculate(textInput, systemId, methodCode)
                        context.putResult(EngineType.QABZ, result)
                    }
                    EngineType.QALB -> {
                        val result = registry.qalbEngine.calculationService.calculate(textInput, systemId, methodCode)
                        context.putResult(EngineType.QALB, result)
                    }
                    EngineType.AKS -> {
                        val result = registry.aksEngine.calculationService.calculate(textInput, systemId, methodCode)
                        context.putResult(EngineType.AKS, result)
                    }
                    EngineType.ISM_AZAM -> {
                        val result = registry.ismAzamEngine.calculateIsmAzam(textInput, secondaryInput, systemId)
                        context.putResult(EngineType.ISM_AZAM, result)
                    }
                    EngineType.ROHANI_TASHKHEES -> {
                        val input = TashkheesInput(name = textInput, mothersName = secondaryInput)
                        val result = registry.tashkheesEngine.pipelineService.runPipeline(input, systemId)
                        context.putResult(EngineType.ROHANI_TASHKHEES, result)
                    }
                }
            } catch (e: Exception) {
                context.putError(engineType, e.message ?: "Unknown error")
                // Log failure, continue if possible (this coordinator tries to continue)
            }
        }
        
        return SharedExecutionResult(
            context = context,
            isSuccess = context.getErrors().isEmpty(),
            summary = "Executed: ${executionPlan.joinToString(", ")}"
        )
    }
}

class JafrEngineManager(
    val registry: EngineRegistry
) {
    private val resolver = EngineResolver()
    private val coordinator = EngineCoordinator(registry, resolver)
    
    suspend fun runEngines(
        enginesToRun: List<EngineType>, 
        textInput: String, 
        systemId: Int,
        secondaryInput: String = "",
        methodCode: String = "HAROOF"
    ): SharedExecutionResult {
        // Validation & Normalization could happen here or in individual engines
        return coordinator.execute(enginesToRun, textInput, systemId, secondaryInput, methodCode)
    }
}
