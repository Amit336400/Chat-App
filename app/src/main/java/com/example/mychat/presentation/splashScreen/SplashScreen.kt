package com.example.mychat.presentation.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mychat.R
import com.example.mychat.ui.theme.SplashScreenGradient

@Preview(showSystemUi = true)
@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize().background(SplashScreenGradient),
        contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.baseline_textsms_24),
            contentDescription = null,
            colorFilter =  ColorFilter.tint(Color.White)

        )
    }
}
