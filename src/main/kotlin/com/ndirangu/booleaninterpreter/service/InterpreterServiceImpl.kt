package com.ndirangu.booleaninterpreter.service

import com.ndirangu.booleaninterpreter.compute.Compute
import com.ndirangu.booleaninterpreter.dto.RequestDto
import com.ndirangu.booleaninterpreter.dto.ResponseDto
import com.ndirangu.booleaninterpreter.util.PrepareResponse
import org.springframework.stereotype.Service

@Service
class InterpreterServiceImpl(private  val compute: Compute,
                             private val prepareResponse: PrepareResponse)
    : InterpreterService {
    override fun interpret(request: RequestDto): ResponseDto {
        val response = compute.computeExpression(request.input)
        return prepareResponse.buildResponse(response)
    }
}