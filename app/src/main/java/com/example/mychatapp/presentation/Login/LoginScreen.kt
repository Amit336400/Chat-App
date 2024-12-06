package com.example.mychatapp.presentation.Login

import com.example.mychatapp.GoogleAuth.GoogleSignInManager
import android.app.Activity
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
import com.example.mychatapp.GoogleAuth.SignInResult
import com.example.mychatapp.GoogleAuth.rememberGoogleSignInLauncher
import com.example.mychatapp.R
import com.example.mychatapp.presentation.navigation.Routes
import com.example.mychatapp.ui.comp.CustomDialog
import com.example.mychatapp.ui.comp.LoadingCPI
import com.example.mychatapp.ui.theme.customColour
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamliners.base.taskState.comp.whenError


@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel
) {
    val activity = LocalContext.current as Activity
    var signInResult by remember { mutableStateOf(SignInResult()) }
    var isLoading by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogErrorMessage by remember { mutableStateOf("") }


    // Google Sign-In launcher
    val launcher = rememberGoogleSignInLauncher(
        activity = activity,
        onSignInResult = { result ->
            signInResult = result
        }
    )

    // Handle sign-in result and login logic
    LaunchedEffect(key1 = signInResult.success) {
        if (signInResult.success) {
            isLoading = false
            val userGmail = Firebase.auth.currentUser?.email
            if (userGmail != null) {
                viewModel.loginBefore(
                    email = userGmail,
                    navHostController = navHostController,
                )
            }else{
                showDialog = true
            }
        }
    }
    viewModel.taskState.whenError {
        isLoading = false
    }

    if (showDialog){
        CustomDialog(
            showDialog = showDialog,
            title = "Email Not Found",
            message ="Email not found , click Go To Login Page ",
            confirmButtonText = "Go To Login Page",
            onConfirm = {
                navHostController.navigate(Routes.LoginScreen)
            })
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
                text = "Welcome to My Chat"
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.3f))

            ElevatedButton(
                onClick = {
                    launcher?.launch(GoogleSignInManager(activity).getSignInIntent())
                    isLoading = true
                }
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Login With Google")
                }
            }
        }

        // Show loading indicator when logging in
        if (isLoading) {
            LoadingCPI(modifier = Modifier.fillMaxSize())
        }
    }
}

