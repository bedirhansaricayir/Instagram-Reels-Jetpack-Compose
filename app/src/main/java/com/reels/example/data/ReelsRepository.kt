package com.reels.example.data

import androidx.compose.runtime.toMutableStateList
import com.reels.example.domain.model.Comment
import com.reels.example.domain.model.Reels
import com.reels.example.domain.model.VideoInfo


val list =
    listOf(
        Reels(
            videoSource = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            videoInfo = VideoInfo(
                title = "Big Buck Bunny",
                description = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.",
                isLiked = false,
                likes = "Beğenmeler",
                commentCount = "115",
                comments = listOf(
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "filmMatik",
                        commentTime = "1h",
                        comment = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                        likeCount = "48",
                        isLiked = false
                    )
                ),
                send = "44,2 bin",
                taggedPeople = listOf("bsaricayir"),
                location = "Türkiye",
                soundImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userTitle = "filmMatik",
                isUserFollowed = false,
                )
        ),
        Reels(
            videoSource = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            videoInfo = VideoInfo(
                title = "We Are Going On Bullrun",
                description = "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500, and posting a video from the road every single day! The only place to watch them is by subscribing to The Smoking Tire or watching at BlackMagicShine.com",
                isLiked = false,
                likes = "Beğenmeler",
                commentCount = "1,8 bin",
                comments = listOf(
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "racingcarUSA",
                        commentTime = "2h",
                        comment = "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500",
                        likeCount = "1243",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "racingcarUSA",
                        commentTime = "2h",
                        comment = "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500",
                        likeCount = "1243",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "racingcarUSA",
                        commentTime = "2h",
                        comment = "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500",
                        likeCount = "1243",
                        isLiked = false
                    )
                ),
                send = "152,8 bin",
                taggedPeople = listOf(
                    "racingcarUSA","Brendon Horst","Jackson Dyx"
                ),
                location = "İzmir, Alsancak",
                soundImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userTitle = "racingcarUSA",
                isUserFollowed = false,
                )
        ),
        Reels(
            videoSource = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            videoInfo = VideoInfo(
                title = "Elephant Dream",
                description = "The first Blender Open Movie from 2006",
                isLiked = false,
                likes = "Beğenmeler",
                commentCount = "356",
                comments = listOf(
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "czgidizi",
                        commentTime = "3h",
                        comment = "The first Blender Open Movie from 2006",
                        likeCount = "233",
                        isLiked = false
                    ),
                    Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "czgidizi",
                        commentTime = "3h",
                        comment = "The first Blender Open Movie from 2006",
                        likeCount = "233",
                        isLiked = false
                    ), Comment(
                        userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                        userTitle = "czgidizi",
                        commentTime = "3h",
                        comment = "The first Blender Open Movie from 2006",
                        likeCount = "233",
                        isLiked = false
                    )
                ),
                send = "74,7 bin",
                taggedPeople = null,
                location = "St.Jersey",
                soundImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userImage = "https://firebasestorage.googleapis.com/v0/b/fitnessapp-d21de.appspot.com/o/images%2FoCaJn8brBQUbn4wGpBYMczzULCG2.jpg?alt=media&token=52a8107b-d0c5-48ca-820e-b8403e795230",
                userTitle = "czgidizi",
                isUserFollowed = false,
                )
        )
    ).toMutableStateList()