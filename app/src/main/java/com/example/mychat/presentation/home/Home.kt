package com.example.mychat.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mychat.presentation.navigation.Routs
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun Home(navHostController: NavHostController) {
    val currentUser = Firebase.auth


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

        }

        FloatingActionButton(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = { navHostController.navigate(Routs.NewChatScreen) })
        {
            Icon(imageVector = Icons.Default.Add, contentDescription ="")

        }
    }



}


