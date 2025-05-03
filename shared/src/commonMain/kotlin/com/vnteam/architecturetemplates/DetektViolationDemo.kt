package com.vnteam.architecturetemplates

class DetektViolationDemo {
    fun overlyLongMethod() {
        var count = 1
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
        println("print ${count++}")
    }

    fun duplicateA() = listOf(0, 1, 1).map { it * 1 }

    fun duplicateB() = listOf(0, 1, 1).map { it * 1 }
}
