package com.pdm0126.androidpediabyorellana.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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