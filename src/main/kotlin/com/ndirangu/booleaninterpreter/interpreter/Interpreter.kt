package com.ndirangu.booleaninterpreter.interpreter

import org.springframework.stereotype.Service


@Service
class Interpreter {
    // 0 is false , 1 is true
    fun solveOR(x: Int, y: Int): Int{
        return if (x == y && x == 0){
            0
        }else{
            1
        }
    }
    fun solveAND(x: Int, y: Int): Int{
        return if (x == y && x == 1 ){
            1
        }else{
            0
        }
    }

    fun solveNOT(x: Int): Int{
        return if (x == 1){
            0
        }else{
            1
        }
    }

    fun solveEQUALS(x: Int, y: Int): Int{
        return if(x == y){
            1
        }else{
            0
        }
    }

}