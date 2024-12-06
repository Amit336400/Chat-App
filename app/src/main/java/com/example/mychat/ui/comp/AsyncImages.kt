package com.example.mychat.ui.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageResult
import com.example.mychat.R


@Composable
fun AsyncImages(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    uri : String,
) {
    SubcomposeAsyncImage(
        model = uri,
        modifier = modifier
            .run {
                onClick?.let { clickable {   it() }} ?: this
            },
        contentScale = ContentScale.FillBounds,
        loading = {ImageLoading()},
        contentDescription =null
    )
}