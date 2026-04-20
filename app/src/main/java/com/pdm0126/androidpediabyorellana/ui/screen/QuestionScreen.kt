package com.pdm0126.androidpediabyorellana.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pdm0126.androidpediabyorellana.Question

@Composable
fun Questions(
    question: Question,
    questionNumber: Int,
    totalQuestions: Int,
    score: Int,
    onNext: (Boolean) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    var answered by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Pregunta $questionNumber / $totalQuestions")
        Text("Puntaje: $score")

        Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                question.question,
                modifier = Modifier.padding(16.dp)
            )
        }

        question.option.forEachIndexed { index, option ->
            val borderColor = when {
                !answered -> Color.Gray
                option == question.correctAnswer -> Color.Green
                index == selectedIndex -> Color.Red
                else -> Color.LightGray
            }

            Button(
                onClick = {
                    if (!answered) {
                        selectedIndex = index
                        answered = true
                    }
                },
                enabled = !answered,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                border = BorderStroke(2.dp, borderColor),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(10 .dp))

        if (answered) {
            Text(question.funFact)
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    val selectedOption =
                        if (selectedIndex != -1) question.option[selectedIndex] else ""
                    val isCorrect = selectedOption == question.correctAnswer
                    selectedIndex = -1
                    answered = false
                    onNext(isCorrect)
                }
            ) {
                Text(
                    if (questionNumber == totalQuestions)
                        "Ver Resultado"
                    else
                        "Siguiente"
                )
            }
        }
    }
}