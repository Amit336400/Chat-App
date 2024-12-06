package com.example.mychatapp.presentation.editProfileScreen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mychatapp.R
import com.example.mychatapp.domain.ext.currentUser
import com.example.mychatapp.domain.model.Gender
import com.example.mychatapp.domain.model.User
import com.example.mychatapp.presentation.common.CustomEditText
import com.example.mychatapp.presentation.navigation.Routes
import com.example.mychatapp.ui.comp.CustomDialog
import com.example.mychatapp.ui.comp.ProfileImagePicker
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.streamliners.base.taskState.comp.whenLoading
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold

@Composable
fun EditProfileScreen(
    navHostController: NavHostController,
    viewModel: EditProfileViewModel,
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    // Using ViewModel to manage states properly
    var name by remember { mutableStateOf("${auth.currentUser?.displayName}") }
    var email by remember { mutableStateOf("${auth.currentUser?.email}") }
    var bio by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var bioError by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<String?>(null) }

    var userUidNotFound by remember { mutableStateOf<Boolean>(false) }


    // Gender state
    var selectedGender by remember { mutableStateOf<String?>(null) }
    val genderOptions = Gender.values().map { it.name } // Convert enum to list of strings

    // Media picker for profile image
    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null){
            imageUri = uri.toString()
        }

    }

    LaunchedEffect(key1 = Unit) {
        val currentUser = Firebase.auth.currentUser
        userUidNotFound = currentUser?.uid.isNullOrEmpty()
    }

    if (userUidNotFound) {
        CustomDialog(
            showDialog = userUidNotFound,
            title = "Login Failed ",
            message = "Please Login Again",
            confirmButtonText = "Retry",
            onConfirm = {
                val googleSignInClient =
                    GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
                googleSignInClient.signOut().addOnCompleteListener {
                    navHostController.navigate(Routes.LoginScreen)
                }
            })
    }

    TitleBarScaffold(title = "Edit Profile") {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Profile image area
                Card() {
                    ProfileImagePicker(
                        defaultIconResId = R.drawable.person_24,
                        imageUri = if (imageUri == null) null else imageUri.toString()
                    ) {
                        pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Name Input
                CustomEditText(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { inputName ->
                        name = inputName
                        nameError = inputName.isBlank()
                    },
                    label = "Name",
                    isError = nameError,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    errorMessage = "Name is required"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Display email (disabled)
                CustomEditText(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { /* No-op */ },
                    label = "Email",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Bio Input
                CustomEditText(
                    modifier = Modifier.fillMaxWidth(),
                    value = bio,
                    onValueChange = { inputBio ->
                        bio = inputBio
                        bioError = inputBio.isBlank()
                    },
                    label = "Bio",
                    isError = bioError,
                    maxLines = 3,
                    leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
                    errorMessage = "Bio is required"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Gender Selection
                Column {
                    Text(text = "Gender")

                    // Iterate through gender options (strings)
                    genderOptions.forEach { gender ->
                        GenderButton(gender = gender, isSelected = selectedGender) {
                            selectedGender = gender
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Save Button with validation
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Cyan),
                    onClick = {
                        if (name.isNotBlank()) {
                            val user = User(
                                id = currentUser(),
                                name = name,
                                email = email,
                                bio = if (bio.isBlank()) null else bio,
                                gender = selectedGender,
                                imageUri = imageUri
                            )

                            viewModel.saveUser(user,
                                onSuccess = {
                                    Toast.makeText(
                                        context,
                                        "Profile Saved Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navHostController.navigate(Routes.HomeScreen)
                                })
                        } else {
                            nameError = true
                        }
                    }
                ) {
                    Text(color = Color.White, text = "Save")
                }
            }

            viewModel.saveProfileTask.whenLoading {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}


