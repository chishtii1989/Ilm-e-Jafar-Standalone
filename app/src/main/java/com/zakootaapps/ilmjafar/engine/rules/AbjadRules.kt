package com.zakootaapps.ilmjafar.engine.rules

object AbjadRules {
    fun reduceToSingleDigit(num: Int): Int {
        var current = num
        while (current > 9) {
            var sum = 0
            var temp = current
            while (temp > 0) {
                sum += temp % 10
                temp /= 10
            }
            current = sum
        }
        return current
    }

    fun calculateSpiritualNumber(totalAbjad: Int, lettersCount: Int): Int {
        return totalAbjad + lettersCount
    }
}
