package com.example.mychatapp.presentation.chatScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mychatapp.domain.ext.currentUserId
import com.example.mychatapp.domain.model.Channel

@Composable
fun MassageList(channel: Channel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(channel.messages) {
            val selfSend = currentUserId() == it.sender
            Box(modifier =Modifier.fillMaxWidth(),
                contentAlignment = if(selfSend) Alignment.BottomEnd else Alignment.CenterStart) {

                MassageCard(
                    massage = it
                )
            }
        }
    }
}


