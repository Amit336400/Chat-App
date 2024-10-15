package com.example.mychat.presentation.Login

import GoogleSignInManager
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.googleauth.data.SignInResult
import com.example.googleauth.data.rememberSignInLauncher
import com.example.mychat.R
import com.example.mychat.ui.theme.customColour
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun LoginScreen() {

    val activityContext = LocalContext.current as Activity

    val googleSignInManager = remember {
        GoogleSignInManager(activityContext)
    }

    var signInResult by remember { mutableStateOf(SignInResult()) }

    val launcher = rememberSignInLauncher(
        onSignInResult = {
            signInResult = it
        }
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customColour)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.5f),
                tint = Color.White,
                painter = painterResource(id = R.drawable.baseline_textsms_24),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                text = "Welcome to My Chat "
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.3f))

            ElevatedButton(
                onClick = {
                    if (launcher != null) {
                        launcher.launch(googleSignInManager.signIn())

                    }
                },

                ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Login With Google ${signInResult.data?.displayName}")
                }
            }
        }
    }
}