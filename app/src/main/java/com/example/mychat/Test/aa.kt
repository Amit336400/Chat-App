package com.example.mychat.Test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerApp() {
    var red by remember { mutableStateOf(0f) }   // Red value (0 to 255)
    var green by remember { mutableStateOf(0f) } // Green value (0 to 255)
    var blue by remember { mutableStateOf(0f) }  // Blue value (0 to 255)

    val currentColor = Color(red / 255f, green / 255f, blue / 255f) // Create color using RGB

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color display box
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(currentColor)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Red Slider
        Text(text = "Red: ${red.toInt()}")
        Slider(
            value = red,
            onValueChange = { red = it },
            valueRange = 0f..255f,
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Red
            )
        )

        // Green Slider
        Text(text = "Green: ${green.toInt()}")
        Slider(
            value = green,
            onValueChange = { green = it },
            valueRange = 0f..255f,
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green
            )
        )

        // Blue Slider
        Text(text = "Blue: ${blue.toInt()}")
        Slider(
            value = blue,
            onValueChange = { blue = it },
            valueRange = 0f..255f,
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                activeTrackColor = Color.Blue
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorPickerAppPreview() {
    ColorPickerApp()
}
