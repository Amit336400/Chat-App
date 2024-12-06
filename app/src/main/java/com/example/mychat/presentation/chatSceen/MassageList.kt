package com.example.mychat.presentation.chatSceen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mychat.domain.Ext.currentUser
import com.example.mychat.domain.model.Channel

@Composable
fun MassageList(channel: Channel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(channel.messages) {
            var selfSend = currentUser() == it.sender
            Box(modifier =Modifier.fillMaxWidth(),
                contentAlignment = if(selfSend) Alignment.BottomEnd else Alignment.CenterStart) {

                MassageCard(
                    massage = it
                )
            }
        }
    }
}


