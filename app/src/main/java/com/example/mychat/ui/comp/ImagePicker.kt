package com.example.mychat.ui.comp

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter


@Composable
fun ProfileImagePicker(
    defaultIconResId: Int, // Pass the default drawable resource ID
    imageUri : String ? = null,
    onImageUploadClick: () -> Unit // Callback to handle image upload clicks
) {
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // UI Component
    if (imageUri != null) {
        // Show selected image when URI is available
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "Selected Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { onImageUploadClick() } // Open media picker again
        )
    } else {
        // Show default camera icon
        Icon(
            painter = painterResource(id = defaultIconResId),
            contentDescription = "Upload Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { onImageUploadClick() },
            tint = Color.Gray
        )
    }
}
