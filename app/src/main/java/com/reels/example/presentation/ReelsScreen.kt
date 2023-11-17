package com.reels.example.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.reels.example.data.list
import com.reels.example.presentation.components.ExploreVideoPlayer
import com.reels.example.presentation.components.LeftBottomSection
import com.reels.example.presentation.components.RightBottomSection
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.abs


@SuppressLint("UnsafeOptInUsageError")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen() {
    val pagerState = rememberPagerState()

    val flingBehaviour = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(1)
    )
    var isMuted by remember {
        mutableStateOf(false)
    }
    val onLiked = remember {
        { index: Int, liked: Boolean ->
            list[index] =
                list[index].copy(videoInfo = list[index].videoInfo.copy(isLiked = liked))
        }
    }
    val onFollowClicked = remember {
        { index: Int, isFollowed: Boolean ->
            list[index] = list[index].copy(videoInfo = list[index].videoInfo.copy(isUserFollowed = isFollowed))
        }
    }
    val isFirstItem by remember(pagerState) {
        derivedStateOf {
            pagerState.currentPage == 0
        }
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }
    Box {
        VerticalPager(
            modifier = Modifier.fillMaxHeight(),
            state = pagerState,
            beyondBoundsPageCount = 0,
            flingBehavior = flingBehaviour,
            pageCount = list.size
        ) { index ->
            val shouldPlay by remember(pagerState) {
                derivedStateOf {
                    (abs(pagerState.currentPageOffsetFraction) < .5 && pagerState.currentPage == index) || (abs(
                        pagerState.currentPageOffsetFraction
                    ) > .5 && pagerState.targetPage == index)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ExploreVideoPlayer(
                    reel = list[index],
                    shouldPlay = shouldPlay,
                    isMuted = isMuted,
                    onMuted = {
                        isMuted = it
                    },
                    onDoubleTap = {
                        onLiked(index, it)
                    },
                    isScrolling = pagerState.isScrollInProgress
                )
                LeftBottomSection(
                    modifier = Modifier.align(Alignment.BottomStart),
                    userImage = list[index].videoInfo.userImage,
                    userTitle = list[index].videoInfo.userTitle,
                    isFollowed = list[index].videoInfo.isUserFollowed,
                    videoDescription = list[index].videoInfo.description,
                    soundTitle = list[index].videoInfo.description,
                    onFollowButtonClicked = {
                        onFollowClicked(index,!list[index].videoInfo.isUserFollowed)
                    }
                )
                RightBottomSection(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    isLiked = list[index].videoInfo.isLiked,
                    likes = list[index].videoInfo.likes,
                    comment = list[index].videoInfo.comments,
                    send = list[index].videoInfo.send,
                    image = list[index].videoInfo.soundImage,
                    onFavoriteClicked = {
                        onLiked(index, !list[index].videoInfo.isLiked)
                    }
                )
            }
        }
    }
}