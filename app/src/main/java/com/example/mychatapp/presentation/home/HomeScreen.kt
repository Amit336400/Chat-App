package com.example.mychatapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mychatapp.domain.Ext.id
import com.example.mychatapp.domain.model.Channel
import com.example.mychatapp.presentation.navigation.Routes
import com.example.mychatapp.ui.comp.AsyncImages
import com.example.mychatapp.ui.comp.LoadingCPI
import com.example.mychatapp.ui.theme.lightBlue
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.base.taskState.comp.whenLoading
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold
import com.streamliners.compose.comp.CenterText

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

                viewModel.channelsState.whenLoading {
                    LoadingCPI(modifier = Modifier.fillMaxSize())
                }
                viewModel.channelsState.whenLoaded { listOfChannel ->
                    ChannelCard(listOfChannel,navHostController)
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = { navHostController.navigate(Routes.NewChatScreen) })
            {
                Icon(imageVector = Icons.Default.Add, contentDescription ="")

            }
        }
    }
}

