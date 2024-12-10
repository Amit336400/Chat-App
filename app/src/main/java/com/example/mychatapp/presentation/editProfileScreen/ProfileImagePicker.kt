package com.example.mychatapp.presentation.editProfileScreen

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
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.streamliners.pickers.media.PickedMedia


@Composable
fun ProfileImagePicker(
    defaultIconResId: Int, // Pass the default drawable resource ID
    imageUri : PickedMedia ? = null,
    onImageUploadClick: () -> Unit // Callback to handle image upload clicks
) {

    if (imageUri != null) {
      AsyncImage(
          modifier = Modifier.clickable { onImageUploadClick() }
              .size(100.dp)
              .clip(CircleShape),
          model = imageUri.uri, contentDescription = null)
    } else {
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
