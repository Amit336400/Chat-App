package com.example.mychat.presentation.UserData

/**
 * this page collect User data and store in database
 * this page visible one time when user login
 */

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mychat.R
import com.example.mychat.presentation.common.CustomEditText


@Preview(showSystemUi = true)
@Composable
fun GetUserData(modifier: Modifier = Modifier) {
    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var bio by remember {
        mutableStateOf("")
    }

    var nameError by remember { mutableStateOf(false) }
    var bioError by remember { mutableStateOf(false) }

    val gender = listOf("Male", "Female", "Other")
    var selectGender by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            onClick = { /*TODO*/ }) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.camera_enhance_24),
                    contentDescription = null
                )

            }

        }

        /**
         * This EditView collect User Name
         */
        CustomEditText(
            modifier = Modifier.fillMaxWidth(),
            value = name, onValueChange = {
                name = it
                nameError = if (name.isBlank()) true else false
            },
            label = "Name",
            isError = nameError,
            maxLines = 1,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            errorMessage = "Required"
        )
        /**
         * This EditText only display User Email
         * only readable this EditText
         */
        CustomEditText(
            modifier = Modifier.fillMaxWidth(),

            value = "amitkundu.developer@gmail.com",

            onValueChange = { email = it },
            maxLines = 1,
            label = "Email",
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            enabled = false

        )


        /**
         * This EditText collect User Bio
         */

        CustomEditText(
            modifier = Modifier.fillMaxWidth(),
            value = bio, onValueChange = {
                bio = it
                bioError = if (bio.isBlank()) true else false
            },
            maxLines = 3,
            label = "Bio",
            leadingIcon = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            },
            errorMessage = "Required",
            isError = bioError
        )


        Spacer(modifier = Modifier.height(10.dp))
        /**
         * This is a radio Button that collect user Gender
         */
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            ),
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Gender")
                LazyColumn {
                    items(gender) {
                        GenderButton(
                            gender = it, isSelected = selectGender
                        ) {
                            selectGender = it
                        }
                    }
                }
            }

        }



Spacer(modifier = Modifier.height(10.dp))
        /**
         * This Button Save the user data in DataBase
         * and check name is blank or not
         * blank "" , "  "
         * Empty ""
         */
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.Cyan,
            ),
            onClick = {
                if (name.isNotBlank()) {

                    // TODO function to store data in database

                    Toast.makeText(context, "Data Save", Toast.LENGTH_SHORT).show()
                } else {
                    nameError = true
                }

            },
        ) {
            Text(
                color = Color.Yellow,
                text = "Save"
            )
        }
    }
}

@Composable
fun GenderButton(
    gender: String,
    isSelected: String,
    onClick: () -> Unit,

    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = gender == isSelected, onClick = onClick)
        Text(text = "$gender")
    }


}


