package com.example.googleauth.data

import GoogleSignInManager
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.mychat.presentation.navigation.Routs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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







