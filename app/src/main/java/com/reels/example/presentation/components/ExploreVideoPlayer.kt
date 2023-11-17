package com.reels.example.presentation.components

import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.reels.example.R
import com.reels.example.domain.model.Reels
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@UnstableApi
@Composable
fun ExploreVideoPlayer(
    reel: Reels,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    onDoubleTap: (Boolean) -> Unit,
    isScrolling: Boolean
) {
    val exoPlayer = rememberExoPlayerWithLifeCycle(reel.videoSource)
    val playerView = rememberPlayerView(exoPlayer)
    var volumeIconVisibility by remember { mutableStateOf(false) }
    var likeIconVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box {
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .pointerInput(reel.videoInfo.isLiked, isMuted) {
                    detectTapGestures(
                        onDoubleTap = {
                            onDoubleTap(true)
                            coroutineScope.launch {
                                likeIconVisibility = true
                                delay(800)
                                likeIconVisibility = false
                            }
                        },
                        onTap = {
                            if (exoPlayer.playWhenReady) {
                                if (isMuted.not()) {
                                    exoPlayer.volume = 0f
                                    onMuted(true)
                                } else {
                                    exoPlayer.volume = 1f
                                    onMuted(false)
                                }
                                coroutineScope.launch {
                                    volumeIconVisibility = true
                                    delay(800)
                                    volumeIconVisibility = false
                                }
                            }
                        },
                        onPress = {
                            if (!isScrolling) {
                                exoPlayer.playWhenReady = false
                                awaitRelease()
                                exoPlayer.playWhenReady = true
                            }
                        },
                        onLongPress = {}
                    )
                },
            update = {
                exoPlayer.volume = if (isMuted) 0f else 1f
                exoPlayer.playWhenReady = shouldPlay
            }
        )

        AnimatedVisibility(
            visible = likeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.White.copy(0.90f),
                modifier = Modifier
                    .size(100.dp)
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = volumeIconVisibility,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(color = Color.Black.copy(0.5f), shape = CircleShape)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (isMuted) R.drawable.ic_volume_off else R.drawable.ic_volume_on),
                    contentDescription = null,
                    tint = Color.White.copy(0.9f),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp)
                )
            }
        }
    }

    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}


@UnstableApi
@Composable
fun rememberExoPlayerWithLifeCycle(
    videoSource: String
): ExoPlayer {

    val context = LocalContext.current
    val exoPlayer = remember(videoSource) {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
                repeatMode = Player.REPEAT_MODE_ONE
                setHandleAudioBecomingNoisy(true)
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(videoSource))

                setMediaSource(source)
                prepare()
            }
    }
    var appInBackground by remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, appInBackground) {
        val lifecycleObserver = getExoPlayerLifecycleObserver(exoPlayer, appInBackground) {
            appInBackground = it
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
    return exoPlayer
}

fun getExoPlayerLifecycleObserver(
    exoPlayer: ExoPlayer,
    wasAppInBackground: Boolean,
    setWasAppInBackground: (Boolean) -> Unit
): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                if (wasAppInBackground)
                    exoPlayer.playWhenReady = true
                setWasAppInBackground(false)
            }

            Lifecycle.Event.ON_PAUSE -> {
                exoPlayer.playWhenReady = false
                setWasAppInBackground(true)
            }

            Lifecycle.Event.ON_STOP -> {
                exoPlayer.playWhenReady = false
                setWasAppInBackground(true)
            }

            Lifecycle.Event.ON_DESTROY -> {
                exoPlayer.release()
            }

            else -> {}
        }
    }

@UnstableApi
@Composable
fun rememberPlayerView(exoPlayer: ExoPlayer): PlayerView {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            player = exoPlayer
            setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
        }
    }
    DisposableEffect(key1 = true) {
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}