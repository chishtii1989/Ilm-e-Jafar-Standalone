package com.zakootaapps.ilmjafar.domain.tashkhees.engine

import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository
import com.zakootaapps.ilmjafar.data.repository.naqoosh_knowledge.NaqooshKnowledgeRepository
import com.zakootaapps.ilmjafar.data.repository.talismat_knowledge.TalismatKnowledgeRepository
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.takseer.TakseerEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.bast.BastEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qabz.QabzEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qalb.QalbEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.aks.AksEngine
import com.zakootaapps.ilmjafar.domain.ismazam.engine.IsmAzamEngine

data class TashkheesInput(
    val name: String,
    val mothersName: String,
    val dateOfBirth: String? = null,
    val gender: String? = null,
    val notes: String? = null
)

data class RohaniFinding(
    val category: String,
    val findingText: String,
    val references: List<String>,
    val severityScore: Int
)

data class RohaniResult(
    val inputSummary: String,
    val systemUrduName: String,
    val executedModules: List<String>,
    val ruleMatches: List<String>,
    val findings: List<RohaniFinding>,
    val confidenceScore: Double
)

class InputValidationService {
    fun validate(input: TashkheesInput): Boolean {
        if (input.name.isBlank()) return false
        if (input.mothersName.isBlank()) return false
        return true
    }
}

class ConfidenceCalculator {
    fun calculate(findings: List<RohaniFinding>): Double {
        return 85.0 // Placeholder
    }
}

class KnowledgeRuleEvaluator(
    private val jafrRepository: JafrKnowledgeRepository,
    private val naqooshRepository: NaqooshKnowledgeRepository,
    private val talismatRepository: TalismatKnowledgeRepository
) {
    suspend fun evaluate(abjadTotal: Int): List<String> {
        return listOf("مطابقت عددی", "تکسیر ہم وزن")
    }
}

class FindingGenerator {
    fun generate(ruleMatches: List<String>): List<RohaniFinding> {
        return listOf(
            RohaniFinding(
                category = "General",
                findingText = "Initial analysis complete.",
                references = listOf("Knowledge DB"),
                severityScore = 1
            )
        )
    }
}

class AnalysisPipeline(
    private val abjadEngine: AbjadEngine,
    private val takseerEngine: TakseerEngine,
    private val bastEngine: BastEngine,
    private val qabzEngine: QabzEngine,
    private val qalbEngine: QalbEngine,
    private val aksEngine: AksEngine,
    private val ismAzamEngine: IsmAzamEngine
) {
    suspend fun execute(input: TashkheesInput, systemId: Int): Pair<Int, List<String>> {
        val combinedText = "${input.name} ${input.mothersName}"
        val abjadResult = abjadEngine.calculationService.performCalculation(combinedText, systemId)

        val executedModules = listOf(
            "Abjad Analysis", 
            "Takseer Analysis", 
            "Bast Analysis",
            "Qabz Analysis", 
            "Qalb Analysis", 
            "Aks Analysis", 
            "Ism-e-Azam Matching"
        )

        return Pair(abjadResult.finalTotal, executedModules)
    }
}

class RohaniAnalyzer(
    private val pipeline: AnalysisPipeline,
    private val ruleEvaluator: KnowledgeRuleEvaluator,
    private val findingGenerator: FindingGenerator,
    private val confidenceCalculator: ConfidenceCalculator
) {
    suspend fun analyze(input: TashkheesInput, systemId: Int): RohaniResult {
        val (abjadTotal, executedModules) = pipeline.execute(input, systemId)
        val rules = ruleEvaluator.evaluate(abjadTotal)
        val findings = findingGenerator.generate(rules)
        val confidence = confidenceCalculator.calculate(findings)

        return RohaniResult(
            inputSummary = "${input.name} بن/بنت ${input.mothersName}",
            systemUrduName = "ابجد", // Will be dynamic based on systemId
            executedModules = executedModules,
            ruleMatches = rules,
            findings = findings,
            confidenceScore = confidence
        )
    }
}

class ResultFormatter {
    fun format(result: RohaniResult): String {
        val sb = StringBuilder()
        sb.append("Input Summary: ${result.inputSummary}\n")
        sb.append("System: ${result.systemUrduName}\n")
        sb.append("Confidence: ${result.confidenceScore}%\n")
        return sb.toString()
    }
}

class PipelineService(private val analyzer: RohaniAnalyzer) {
    suspend fun runPipeline(input: TashkheesInput, systemId: Int): RohaniResult {
        return analyzer.analyze(input, systemId)
    }
}

class KnowledgeEvaluationService(private val evaluator: KnowledgeRuleEvaluator) {
    suspend fun evaluateKnowledge(value: Int) = evaluator.evaluate(value)
}

class RohaniTashkheesEngine(
    private val abjadEngine: AbjadEngine,
    private val takseerEngine: TakseerEngine,
    private val bastEngine: BastEngine,
    private val qabzEngine: QabzEngine,
    private val qalbEngine: QalbEngine,
    private val aksEngine: AksEngine,
    private val ismAzamEngine: IsmAzamEngine,
    private val jafrRepository: JafrKnowledgeRepository,
    private val naqooshRepository: NaqooshKnowledgeRepository,
    private val talismatRepository: TalismatKnowledgeRepository
) {
    val validationService = InputValidationService()
    
    private val ruleEvaluator = KnowledgeRuleEvaluator(jafrRepository, naqooshRepository, talismatRepository)
    private val findingGenerator = FindingGenerator()
    private val confidenceCalculator = ConfidenceCalculator()
    
    private val pipeline = AnalysisPipeline(
        abjadEngine, takseerEngine, bastEngine, qabzEngine, qalbEngine, aksEngine, ismAzamEngine
    )
    
    private val analyzer = RohaniAnalyzer(pipeline, ruleEvaluator, findingGenerator, confidenceCalculator)

    val pipelineService = PipelineService(analyzer)
    val knowledgeEvaluationService = KnowledgeEvaluationService(ruleEvaluator)
    val formatter = ResultFormatter()
}
