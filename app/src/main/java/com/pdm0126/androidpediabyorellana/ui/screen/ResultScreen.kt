package com.pdm0126.androidpediabyorellana.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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