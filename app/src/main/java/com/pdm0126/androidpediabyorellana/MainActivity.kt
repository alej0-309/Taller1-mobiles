package com.pdm0126.androidpediabyorellana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPediaApp()
        }
    }

    @Composable
    fun AndroidPediaApp(modifier: Modifier = Modifier){
        //Welcome 1, Question 2, Result 3
        var screen by remember { mutableIntStateOf(1) }

        var currentIndex by remember { mutableIntStateOf(0) }
        var score by remember { mutableIntStateOf(0) }

        val questionsList = quizQuestions

        when (screen) {
            1-> Welcome(){
                screen = 2
            }
            2-> Questions(
                question = questionsList[currentIndex],
                questionNumber = currentIndex + 1,
                totalQuestions = 3,
                score = score,
                onNext = { isCorrect ->
                    if (isCorrect) score += 100;
                    if(currentIndex < questionsList.lastIndex){
                        currentIndex++
                    }else {
                        screen = 3;
                    }
                }
            )
            3-> ResultScreen(score) {
                score = 0
                currentIndex = 0
                screen = 1
            }
        }
    }

    @Composable
    fun Welcome(onStart: () -> Unit){

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("AndroidPedia", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(10.dp))

            Text("¿Cuánto sabes de Android?")
            Text("Alejandro Orellana 00125720")

            Button(onClick = onStart, modifier = Modifier.padding(10.dp)) {
                Text("Comenzar Quiz")
            }
        }
    }

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

    @Composable
    fun ResultScreen(score: Int, onRestart: () -> Unit){
        val message = when (score) {
            300 -> "¡Increible manito supiste todas toditas! GG ez desinstalen"
            200 -> "Ta bien 👍"
            100 -> "Peor es nada..."
            else -> "Ta mal 👎"
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Resultado Final", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Obtuviste $score de 300 puntos")
            Text(message)

            Button(onClick = onRestart) {
                Text("Reiniciar")
            }
        }
    }
}

