package com.reels.example.domain.model

data class Reels(
    val videoSource: String,
    val videoInfo: VideoInfo
)

data class VideoInfo(
    val title: String,
    val description: String,
    val isLiked: Boolean,
    val likes: String,
    val commentCount: String,
    val comments: List<Comment>,
    val send: String,
    val taggedPeople: List<String>? = null,
    val location: String? = null,
    val soundImage: String,
    val userImage: String,
    val userTitle: String,
    val isUserFollowed: Boolean,
)

data class Comment(
    val userImage: String,
    val userTitle: String,
    val commentTime: String,
    val comment: String,
    val likeCount: String,
    val isLiked: Boolean
)
