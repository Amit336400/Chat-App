package com.example.mychat.presentation.chatSceen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mychat.domain.model.Message
import com.example.mychat.ui.theme.lightBlue

@Composable
fun MassageCard(massage : Message) {


        Card(
            modifier = Modifier.padding(3.dp),
            colors = CardDefaults.cardColors(
                containerColor = lightBlue
            ),

            ) {
                Column {
                    Text(
                        text = massage.message,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                    Text(
                        text = massage.time.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }

            }

        }




