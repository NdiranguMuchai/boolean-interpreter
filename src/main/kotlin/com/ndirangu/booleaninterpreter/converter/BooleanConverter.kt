package com.ndirangu.booleaninterpreter.converter

import org.springframework.stereotype.Component

@Component
class BooleanConverter {

    fun convert(input : String): String{
        val trueReplaced = replaceTRUE(input)
        val falseReplaced = replaceFALSE(trueReplaced)


        if (falseReplaced.equals(input, true)){
            return "-1"
        }

        return falseReplaced
    }


    fun replaceTRUE(input : String): String{
        return checkBoolean(input = input, replacement = "1", word = "true", letter = "t")
    }

    fun replaceFALSE(input : String): String{
        return checkBoolean(input = input, replacement = "0", word = "false", letter = "f")
    }

    fun checkBoolean(input: String, replacement: String, word: String, letter: String):String{
        var updated = input.lowercase()

        if (input.contains(word, true)){
            updated = updated.replace(word.lowercase().toRegex(), replacement)
        }

        if (input.contains(letter, true)){
            updated = updated.replace(letter.lowercase().toRegex(), replacement)
        }

        return updated
    }


}