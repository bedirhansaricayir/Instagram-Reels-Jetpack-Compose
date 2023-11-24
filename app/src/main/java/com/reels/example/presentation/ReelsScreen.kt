package com.reels.example.presentation


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.reels.example.data.list
import com.reels.example.domain.model.Comment
import com.reels.example.presentation.components.CommentItemWithConstraint
import com.reels.example.presentation.components.ExploreVideoPlayer
import com.reels.example.presentation.components.LeftBottomSection
import com.reels.example.presentation.components.RightBottomSection
import com.reels.example.presentation.components.TaggedPeopleBottomSheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.abs


@SuppressLint("UnsafeOptInUsageError")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReelsScreen() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false,
            initialValue = SheetValue.Hidden,
            density = LocalDensity.current
        )
    )


    var commentListIndex by remember { mutableIntStateOf(0) }
    var commentList by remember { mutableStateOf(emptyList<Comment>()) }

    var taggedPeopleList by remember { mutableStateOf(emptyList<String>()) }


    var bottomSheetVisible by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    val pagerState = rememberPagerState { list.size }
    val flingBehaviour = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(1)
    )

    var isMuted by remember { mutableStateOf(false) }
    val onLiked = remember {
        { index: Int, liked: Boolean ->
            list[index] =
                list[index].copy(videoInfo = list[index].videoInfo.copy(isLiked = liked))
        }
    }
    val onLikedComment = remember {
        { commentListIndex: Int, clickedCommentIndex: Int ->

            val updatedComments = list[commentListIndex].videoInfo.comments.toMutableStateList()
            updatedComments[clickedCommentIndex] = updatedComments[clickedCommentIndex].copy(
                isLiked = !updatedComments[clickedCommentIndex].isLiked
            )

            list[commentListIndex] = list[commentListIndex].copy(
                videoInfo = list[commentListIndex].videoInfo.copy(comments = updatedComments)
            )

            commentList = list[commentListIndex].copy(
                videoInfo = list[commentListIndex].videoInfo.copy(comments = updatedComments)
            ).videoInfo.comments


        }
    }
    val onFollowClicked = remember {
        { index: Int, isFollowed: Boolean ->
            list[index] =
                list[index].copy(videoInfo = list[index].videoInfo.copy(isUserFollowed = isFollowed))
        }
    }
    val onCommentClicked: (Int) -> List<Comment> = remember {
        { index: Int ->
            list[index].videoInfo.comments
        }
    }

    val onTaggedPeopleClicked = remember {
        { taggedPeople: List<String> ->
            taggedPeopleList = taggedPeople
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

    if (bottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetVisible = false },
            sheetState = bottomSheetState,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Yorumlar", color = Color.Black, fontWeight = FontWeight.W600)
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        itemsIndexed(commentList) { index, comment ->
                            CommentItemWithConstraint(
                                model = comment,
                                onLikeButtonClick = {
                                    onLikedComment(commentListIndex, index)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetSwipeEnabled = true,
        sheetContent = {
            TaggedPeopleBottomSheet(taggedPeople = taggedPeopleList)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            VerticalPager(
                modifier = Modifier.fillMaxHeight(),
                state = pagerState,
                beyondBoundsPageCount = 1,
                flingBehavior = flingBehaviour,
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
                        videoInfo = list[index].videoInfo,
                        onFollowButtonClicked = {
                            onFollowClicked(index, !list[index].videoInfo.isUserFollowed)
                        },
                        taggedPeopleBarClicked = {
                            onTaggedPeopleClicked(it)
                            scope.launch { scaffoldState.bottomSheetState.expand() }
                        }
                    )
                    RightBottomSection(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        videoInfo = list[index].videoInfo,
                        onFavoriteClicked = {
                            onLiked(index, !list[index].videoInfo.isLiked)
                        },
                        onCommentClicked = {
                            commentList = onCommentClicked(index)
                            commentListIndex = index
                            bottomSheetVisible = true
                        }
                    )
                }
            }
        }
    }
}


