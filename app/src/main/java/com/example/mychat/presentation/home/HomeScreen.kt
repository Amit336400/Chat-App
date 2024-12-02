package com.example.mychat.presentation.home

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
import com.example.mychat.domain.model.Channel
import com.example.mychat.presentation.navigation.Routs
import com.example.mychat.ui.comp.AsyncImages
import com.example.mychat.ui.comp.isLoading
import com.example.mychat.ui.theme.lightBlue
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

                viewModel.channelsState.whenLoading {
                    isLoading(modifier = Modifier.fillMaxSize())
                }
                viewModel.channelsState.whenLoaded { listOfChannei ->
                    ChannelCard(listOfChannei)
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


@Composable
fun ChannelCard(listOfChannei: List<Channel>) {

    LazyColumn(
        modifier = Modifier.padding(1.dp),
        contentPadding = PaddingValues(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(listOfChannei) { channel ->
            Card(
                onClick = {  },
                modifier = Modifier.padding(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lightBlue
                ),

                ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImages(
                        uri = channel.imageUrl!!,

                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),

                        )
                    Column {
                        Text(
                            text = channel.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )

                    }
                }
            }


        }
    }


}