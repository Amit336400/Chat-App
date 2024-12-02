package com.example.mychat.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mychat.presentation.navigation.Routs
import com.example.mychat.ui.comp.UserCard
import com.example.mychat.ui.comp.isLoading
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.base.taskState.comp.whenLoading
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold

@Composable
fun Home(navHostController: NavHostController,viewModel: HomeViewModel) {

    LaunchedEffect(key1 = Unit) {
        viewModel.start()
    }
    TitleBarScaffold(title = "Home") {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                viewModel.channels.whenLoading {
                    isLoading(modifier = Modifier.fillMaxSize())
                }
                viewModel.channels.whenLoaded {listOfChannei->
                    LazyColumn {
                     items(listOfChannei){

                     }
                    }

                }

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





}


