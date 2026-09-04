package com.zakootaapps.ilmjafar.engine.jafr.core

import java.lang.IllegalArgumentException

class ValidationEngine {
    fun validate(input: JafrInput): Boolean {
        if (input.name.isBlank()) throw IllegalArgumentException("Name cannot be empty")
        if (input.motherName.isBlank()) throw IllegalArgumentException("Mother name cannot be empty")
        return true
    }
}
