package com.example.mychat.presentation.EditProfile

/**
 * this page collect User data and store in database
 * this page visible one time when user login
 */

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.mychat.R
import com.example.mychat.domain.model.User
import com.example.mychat.presentation.common.CustomEditText
import com.example.mychat.presentation.navigation.Routes
import com.example.mychat.ui.comp.ProfileImagePicker
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamliners.base.taskState.comp.whenLoading


@Composable
fun EditProfileScreen(
    navHostController: NavHostController,
    viewModel: EditProfileViewModel ,
) {
    val context = LocalContext.current
    val auth = Firebase.auth

    // Using ViewModel to manage states properly
    var name by remember { mutableStateOf("${auth.currentUser?.displayName}") }
    var email by remember { mutableStateOf("${auth.currentUser?.email}") }
    var bio by remember { mutableStateOf("hi") }
    var nameError by remember { mutableStateOf(false) }
    var bioError by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female", "Other")
    var selectedGender by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri.toString()
        } else {

        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Profile image area with optimization for image loading
            Card(onClick = { /*TODO*/ }) {
                ProfileImagePicker(
                    defaultIconResId = R.drawable.person_24,
                    imageUri = imageUri
                ) {
                    // pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

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
            Text(text = "Gender")
            Column {
                genderOptions.forEach { gender ->
                    GenderButton(gender = gender, isSelected = selectedGender) {

                        selectedGender = gender
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button with proper validation and ViewModel interaction
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Cyan),
                onClick = {
                    if (name.isNotBlank()) {
                        // Using ViewModel for saving data asynchronously
                        val user = User(
                            id = auth.currentUser?.uid,
                            name = name,
                            email = email,
                            bio = bio,
                            gender = selectedGender,
                            imageUri = imageUri
                        )

                        viewModel.saveUser(user,
                            onSuccess = {

                                Toast.makeText(
                                    context,
                                    "User Login Successfully  ",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
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
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }


    }

}

// GenderButton Composable for selecting gender
@Composable
fun GenderButton(
    gender: String,
    isSelected: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = gender == isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = gender)
    }

}


