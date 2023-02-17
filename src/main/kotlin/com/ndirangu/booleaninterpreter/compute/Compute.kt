package com.ndirangu.booleaninterpreter.compute

import com.ndirangu.booleaninterpreter.converter.BooleanConverter
import com.ndirangu.booleaninterpreter.converter.OperatorConverter
import com.ndirangu.booleaninterpreter.interpreter.Interpreter
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class Compute(private val booleanConverter: BooleanConverter,
              private val operatorConverter: OperatorConverter,
              private val interpreter: Interpreter) {


    fun computeExpression(input: String): String{

        val booleans = booleanConverter.convert(input)
        if (booleans == "-1"){
            return "ER-01"
        }

        val operators = operatorConverter.convertToHumanReadable(booleans)
        if (operators == "UNDEFINED"){
            return "ER-02"
        }

        val removeBrackets = operators.replace("[\\[\\](){}]".toRegex(), "")
        val expressionForComputation = removeBrackets.replace("\\s".toRegex(), "")
        val brokenDown = breakdownEquation(expressionForComputation)
        val solved = solveEquation(brokenDown)
        var updatedExpression = updateEquation(expressionForComputation, brokenDown, solved)

        if(updatedExpression.length == 1){
            return updatedExpression
        }


        do {
            val newBrokenDown = breakdownEquation(updatedExpression)

            val newSolved = solveEquation(newBrokenDown)
            val temp = updatedExpression
            updatedExpression = updateEquation(temp, newBrokenDown, newSolved)
        }
        while (updatedExpression.length != 1)

        return updatedExpression
    }



    fun solveEquation(expression: String): Int{
        val pair = getVariables(expression)
        val x = pair.first
        val y: Int? = pair.second

        return when(expression.replace("[^A-Za-z]".toRegex(), "")){
            "OR" -> interpreter.solveOR(x, y!!)
            "AND" -> interpreter.solveAND(x, y!!)
            "NOT" -> interpreter.solveNOT(x)
            "EQUALS" -> interpreter.solveEQUALS(x, y!!)
            else -> -1
        }


    }

    private fun getVariables(expression: String): Pair<Int, Int?> {
        val digits = expression.filter(Char::isDigit)
        val x = digits[0].digitToInt()
        var y: Int? = null
        if (digits.length > 1) {
            y = digits[1].digitToInt()
        }
        return Pair(x, y)
    }


    fun breakdownEquation(expression: String): String{

        var firstLetter = ""
        var index = -1
        val pattern = Pattern.compile("\\p{Alpha}")
        val matcher = pattern.matcher(expression)

        if (matcher.find()) {
            firstLetter = matcher.group()
            index = matcher.start()
        }

        return getFirstPartOfEquation(expression, firstLetter, index)

    }

    /*
    returns something like:
     1OR0
     1AND0
     NOT0
     */
    fun getFirstPartOfEquation(expression: String, firstLetter: String, index: Int): String{
        return when(firstLetter){
            "O" -> expression.substring(index-1, index+3)
            "A" -> expression.substring(index-1, index+4)
            "N" -> expression.substring(index, index+4)
            "E" -> expression.substring(index-1, index+7)

            else -> "UNDEFINED"
        }
    }


    fun updateEquation(original: String, brokenDown: String, solved: Int): String {
        val remaining = original.length - brokenDown.length
        return if (remaining == 0) {
            solved.toString()
        } else solved.toString() + original.substring(brokenDown.length)
    }

}