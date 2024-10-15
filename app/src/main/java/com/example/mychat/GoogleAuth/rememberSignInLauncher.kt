package com.example.googleauth.data

import GoogleSignInManager
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun rememberSignInLauncher(
    onSignInResult: (SignInResult) -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult>? {
    val context = LocalContext.current

    // Check if the context is an Activity
    if (context !is Activity) {
        // You may log an error or return null based on your error handling strategy
        return null
    }

    // Initialize GoogleSignInManager
    val googleSignInManager = remember { GoogleSignInManager(context) }

    // Update the onSignInResult state to ensure it reflects the latest value
    val updatedOnSignInResult = rememberUpdatedState(onSignInResult)

    // Handle the sign-in result
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result using GoogleSignInManager
        googleSignInManager.handleSignInResult(result.data) { signInResult ->
            updatedOnSignInResult.value(signInResult)
        }
    }
}
