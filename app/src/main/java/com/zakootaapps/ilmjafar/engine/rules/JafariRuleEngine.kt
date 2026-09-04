package com.zakootaapps.ilmjafar.engine.rules

object JafariRuleEngine {
    fun evaluateElement(context: RuleContext): String = ElementRules.evaluate(context)
    fun evaluatePlanet(context: RuleContext): String = PlanetRules.evaluate(context)
    fun evaluateDay(context: RuleContext): String = DayRules.evaluate(context)
    fun evaluateHour(context: RuleContext): String = HourRules.evaluate(context)
    fun evaluateShape(context: RuleContext): String = ShapeRules.evaluate(context)
    fun evaluateInk(context: RuleContext): String = ColourRules.evaluateInk(context)
    fun evaluatePaper(context: RuleContext): String = ColourRules.evaluatePaper(context)
    fun evaluateDirection(context: RuleContext): String = DirectionRules.evaluate(context)
    fun evaluateIsmIlahi(context: RuleContext): String = IsmIlahiRules.evaluate(context)
    fun evaluateMuwakkil(context: RuleContext): String = MuwakkilRules.evaluate(context)
}
