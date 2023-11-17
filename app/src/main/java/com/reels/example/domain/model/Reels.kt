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
    val comments: String,
    val send: String,
    val soundImage: String,
    val userImage: String,
    val userTitle: String,
    val isUserFollowed: Boolean
)