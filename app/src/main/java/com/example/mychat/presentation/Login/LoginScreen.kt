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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavHostController
import com.example.googleauth.data.SignInResult
import com.example.googleauth.data.rememberGoogleSignInLauncher
import com.example.mychat.R
import com.example.mychat.presentation.navigation.Routes
import com.example.mychat.ui.theme.customColour
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel
) {

    val activity = LocalContext.current as Activity
    var signInResult by remember { mutableStateOf(SignInResult()) }
    var isLoading by remember { mutableStateOf(false) }

    // Google Sign-In launcher
    val launcher =
        rememberGoogleSignInLauncher(
            activity = activity,
            onSignInResult = { result ->
                signInResult = result
            }
        )
    LaunchedEffect (
        key1 = signInResult.success
    ){
        if (signInResult.success){
            isLoading = false
            Firebase.auth.currentUser?.email?.let {
                viewModel.loginBefore(it, navHostController,
                    onError = {
                        isLoading = false
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                        navHostController.navigate(Routes.HomeScreen)
                    })
            }
        }
    }
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
                    launcher?.launch(GoogleSignInManager(activity).getSignInIntent())
                    isLoading = true
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
                    Text(text = "Login With Google ")
                }
            }
        }

        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}


