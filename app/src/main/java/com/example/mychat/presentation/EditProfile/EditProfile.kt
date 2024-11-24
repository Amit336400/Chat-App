package com.example.mychat.presentation.EditProfile

/**
 * this page collect User data and store in database
 * this page visible one time when user login
 */

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mychat.R
import com.example.mychat.domain.model.User
import com.example.mychat.presentation.common.CustomEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun SaveUserData(
    navHostController: NavHostController,
    viewModel: EditProfileViewModel = hiltViewModel(),
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



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Profile image area with optimization for image loading
        Icon(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { /* Handle image upload click here */ },
            painter = painterResource(id = R.drawable.camera_enhance_24),
            contentDescription = "Upload Profile Image",
            tint = Color.Gray
        )

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
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            errorMessage = "Name is required"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display email (disabled)
        CustomEditText(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { /* No-op */ },
            label = "Email",
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
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
                        name =  name,
                        email = email,
                        bio = bio,
                        gender = selectedGender
                    )

                    viewModel.saveUser(user) {
                        Toast.makeText(context, "User Login Successfully  ", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    nameError = true
                }
            }
        ) {
            Text(color = Color.White, text = "Save")
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


