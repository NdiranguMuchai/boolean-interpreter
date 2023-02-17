package com.ndirangu.booleaninterpreter.converter

import org.springframework.stereotype.Component

@Component
class OperatorConverter {

    fun convertToHumanReadable(input: String):String{
        val andReplaced = replaceAND(input)
        val orReplaced = replaceOR(andReplaced)
        val notReplaced = replaceNOT(orReplaced)
        val equalsReplaced = replaceEQUALS(notReplaced)

        if (equalsReplaced.equals(input, true)){
            return "UNDEFINED"
        }

        return equalsReplaced
    }

    fun replaceAND(input: String):String{
        return checkOperator(input = input, replacement = "AND", wordOperator = "and", sign = "∧")
    }

    fun replaceOR(input: String):String{
        return checkOperator(input = input, replacement = "OR", wordOperator = "or", sign = "∨")
    }

    fun replaceNOT(input: String):String{
        return checkOperator(input = input, replacement = "NOT", wordOperator = "not", sign = "¬")
    }

    fun replaceEQUALS(input: String):String{
        val checkEquals =  checkOperator(input = input, replacement = "EQUALS", wordOperator = "equals", sign = "=")
        return checkOperator(input = checkEquals, replacement = "EQUALS", wordOperator = "is", sign = "=")
    }

    fun checkOperator(input: String, replacement: String, wordOperator: String, sign: String):String{
        var updated = input.lowercase()

        if (input.contains(wordOperator, true)){
            updated = updated.replace(wordOperator.lowercase().toRegex(), replacement)
        }

        if (input.contains(sign, true)){
            updated = updated.replace(sign.lowercase().toRegex(), replacement)
        }

        return updated.uppercase()
    }
}