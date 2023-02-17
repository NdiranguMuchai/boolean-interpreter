package com.ndirangu.booleaninterpreter.controller

import com.ndirangu.booleaninterpreter.dto.RequestDto
import com.ndirangu.booleaninterpreter.dto.ResponseDto
import com.ndirangu.booleaninterpreter.service.InterpreterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("interpret")
class InterpreterController(private val interpreterService: InterpreterService) {

    @PostMapping
    fun interpret(@RequestBody requestDto: RequestDto): ResponseDto = interpreterService.interpret(requestDto)

}