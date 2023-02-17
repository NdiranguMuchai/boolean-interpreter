package com.ndirangu.booleaninterpreter.service

import com.ndirangu.booleaninterpreter.dto.RequestDto
import com.ndirangu.booleaninterpreter.dto.ResponseDto

interface InterpreterService {
    fun interpret(request: RequestDto): ResponseDto
}