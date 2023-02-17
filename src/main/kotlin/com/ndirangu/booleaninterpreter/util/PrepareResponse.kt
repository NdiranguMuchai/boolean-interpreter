package com.ndirangu.booleaninterpreter.util

import com.ndirangu.booleaninterpreter.dto.ResponseDto
import org.springframework.stereotype.Component

@Component
class PrepareResponse {

    fun buildResponse(computedResponse: String): ResponseDto{
        val response = ResponseDto(code = "", response = "")

        if (computedResponse == "1"){
            response.code = "200"
            response.response = "T"
        }

        if (computedResponse == "0"){
            response.code = "200"
            response.response = "F"
        }

        if (computedResponse == "ER-01"){
            response.code = "ER-01"
            response.response = "Variable(s) not defined in scope"
        }

        if (computedResponse == "ER-02"){
            response.code = "ER-02"
            response.response = "Operator(s) not defined in scope"
        }

        return response
    }
}