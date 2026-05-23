package org.artificery.data

import org.artificery.data.model.Project

fun Project.isValid(): Boolean {
    val validationResults = listOf(
        evaluateStringValidation(name, "ProjectName is PascalCase", ::isPascalCase),
    )

    val invalidResults = validationResults.filterIsInstance<ValidationResult.Invalid>()

    invalidResults.forEach { invalid ->
        println(invalid.reason)
    }

    return invalidResults.isEmpty()
}
        

private fun isPascalCase(input: String) =
    input.matches(Regex("^[A-Z][a-zA-Z0-9]*$"))


private fun evaluateStringValidation(
    value: String,
    evaluationName: String,
    evaluationFunction: (String) -> Boolean,
): ValidationResult = if (evaluationFunction(value)) {
        ValidationResult.Valid
    } else {
        ValidationResult.Invalid("$evaluationName failed for value: $value")
    }

sealed class ValidationResult {
    object Valid : ValidationResult()
    class Invalid(val reason: String) : ValidationResult()
}
        