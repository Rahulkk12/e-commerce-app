package com.example.startupmentor.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val name: String,
    val email: String,
    val startupStage: String,
    val sessionsCount: Int = 0
) : Parcelable

@Parcelize
data class Mentor(
    val id: String,
    val name: String,
    val title: String,
    val company: String,
    val bio: String,
    val expertise: List<String>,
    val rating: Float,
    val reviewCount: Int,
    val sessionPrice30: String,
    val sessionPrice60: String,
    val isAvailable: Boolean,
    val initials: String,
    val avatarColor: Int
) : Parcelable

@Parcelize
data class Session(
    val id: String,
    val mentorName: String,
    val mentorInitials: String,
    val date: String,
    val time: String,
    val duration: String,
    val status: String // "Confirmed" or "Completed"
) : Parcelable

data class Message(
    val id: String,
    val content: String,
    val senderId: String,
    val timestamp: Long,
    val isSent: Boolean
)

data class ChatConversation(
    val id: String,
    val mentorName: String,
    val mentorInitials: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int,
    val avatarColor: Int
)