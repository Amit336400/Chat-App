package com.example.mychat.Test

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mychat.domain.model.User
import com.example.mychat.ui.comp.UserCard

@Composable
fun TestUi(modifier: Modifier = Modifier) {
    val userList = listOf(
        User(
            id = "1",
            name = "Alice Johnson",
            email = "alice.johnson@example.com",
            bio = "Loves painting and outdoor adventures.",
            gender = "Female",
        ),
        User(
            id = "2",
            name = "Bob Smith",
            email = "bob.smith@example.com",
            bio = "Tech enthusiast and avid reader.",
            gender = "Male",
        ),
        User(
            id = "3",
            name = "Cathy Lee",
            email = "cathy.lee@example.com",
            bio = "Passionate about cooking and travel.",
            gender = "Female",
        ),
        User(
            id = "4",
            name = "David Brown",
            email = "david.brown@example.com",
            bio = "Fitness trainer and podcast host.",
            gender = "Male",
        ),
        User(
            id = "5",
            name = "Emma Wilson",
            email = "emma.wilson@example.com",
            bio = "Writer and nature lover.",
            gender = "Female",
        ),
        User(
            id = "6",
            name = "Frank Garcia",
            email = "frank.garcia@example.com",
            bio = "Guitarist and coffee addict.",
            gender = "Male",

        ),
        User(
            id = "7",
            name = "Grace Martinez",
            email = "grace.martinez@example.com",
            bio = "Yoga instructor and wellness coach.",
            gender = "Female",
            imageUri = ""
        ),
        User(
            id = "8",
            name = "Henry Taylor",
            email = "henry.taylor@example.com",
            bio = "Photographer and adventure seeker.",
            gender = "Male",
            imageUri = ""
        ),
        User(
            id = "9",
            name = "Isla Anderson",
            email = "isla.anderson@example.com",
            bio = "Blogger and digital nomad.",
            gender = "Female",
            imageUri = ""
        ),
        User(
            id = "10",
            name = "Jack Miller",
            email = "jack.miller@example.com",
            bio = "Graphic designer and cat dad.",
            gender = "Male",
            imageUri = ""
        )
    )
    



}