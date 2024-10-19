package com.example.mychat.presentation.splashScreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychat.R

@Composable
fun CustomSplashScreen(onTimeout: () -> Unit) {
    // Add delay for splash duration
    val splashDuration = 3000L // 3 seconds
    val alphaAnimation = rememberInfiniteTransition()
    val alpha by alphaAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), // Fade-in animation
            repeatMode = RepeatMode.Restart
        )
    )

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(splashDuration)
        onTimeout()  // Proceed to the next screen after the splash duration
    }

    // Custom Splash Design
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo with fade-in animation
            Image(
                painter = painterResource(id = R.drawable.baseline_email_24),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(150.dp)
                    .alpha(alpha) // Applying alpha for fade-in effect
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App name or tagline
            Text(
                text = "Welcome to ChatApp",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.alpha(alpha)
            )
        }
    }
}
