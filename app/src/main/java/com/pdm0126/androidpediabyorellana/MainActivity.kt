package com.pdm0126.androidpediabyorellana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pdm0126.androidpediabyorellana.ui.screen.Questions
import com.pdm0126.androidpediabyorellana.ui.screen.ResultScreen
import com.pdm0126.androidpediabyorellana.ui.screen.Welcome

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
            1-> Welcome() {
                screen = 2
            }
            2-> Questions(
                question = questionsList[currentIndex],
                questionNumber = currentIndex + 1,
                totalQuestions = 3,
                score = score,
                onNext = { isCorrect ->
                    if (isCorrect) score += 100;
                    if (currentIndex < questionsList.lastIndex) {
                        currentIndex++
                    } else {
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
}

