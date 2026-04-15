package com.pdm0126.androidpediabyorellana

data class Question(
    val id: Int,
    val question: String,
    val option: List<String>,
    val correctAnswer: String,
    val funFact: String
)