package com.example.mychatapp.googleAuth

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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun rememberGoogleSignInLauncher(
    activity: Activity,
    onSignInResult: (SignInResult) -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult>? {
    // Ensure context is an Activity
    val googleSignInManager = remember { GoogleSignInManager(activity) }
    val updatedOnSignInResult = rememberUpdatedState(onSignInResult)

    // Launcher for activity result
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        googleSignInManager.handleSignInResult(result.data) { signInResult ->
            if (signInResult.success) {
                updatedOnSignInResult.value(signInResult)
            }else{
                updatedOnSignInResult.value(signInResult)
            }
        }
    }
}







