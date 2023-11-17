package com.reels.example.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.reels.example.R
import com.reels.example.core.util.noRippleClickable

@Composable
fun LeftBottomSection(
    modifier: Modifier = Modifier,
    userImage: String,
    userTitle: String,
    isFollowed: Boolean,
    videoDescription: String,
    soundTitle: String,
    onFollowButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        UserSection(
            image = userImage,
            title = userTitle,
            isFollowed = isFollowed,
            onFollowButtonClicked = onFollowButtonClicked
        )
        VideoDescription(
            text = videoDescription
        )
        SoundTrackBar(
            text = soundTitle
        )
    }
}

@Composable
fun UserSection(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    isFollowed: Boolean,
    onFollowButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserImage(image = image)
        UserTitle(title = title)
        FollowButton(
            isFollowed = isFollowed,
            onClick = onFollowButtonClicked
        )
    }
}

@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    image: String
) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(image)
        .crossfade(true)
        .build()
    AsyncImage(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        model = request,
        contentScale = ContentScale.Crop,
        contentDescription = "Circular User Image",
    )
}

@Composable
fun UserTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        text = title,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun FollowButton(
    modifier: Modifier = Modifier,
    isFollowed: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = if (isFollowed) "Takip" else "Takip Et",
        color = Color.White,
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color.White),
                RoundedCornerShape(6.dp),
            )
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .animateContentSize()
            .noRippleClickable { onClick() },
        fontSize = 14.sp
    )
}

@Composable
fun VideoDescription(
    modifier: Modifier = Modifier,
    text: String
) {
    val scrollState = rememberScrollState()
    var expandedDesc by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Column(modifier = Modifier.verticalScroll(scrollState).widthIn(max = (screenWidth/2).dp)) {
        Text(
            text = text,
            fontSize = 14.sp,
            maxLines = if (expandedDesc) Int.MAX_VALUE else 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .noRippleClickable {
                    expandedDesc = !expandedDesc
                }
                .animateContentSize()
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SoundTrackBar(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(1.dp,Gray.copy(0.5f))
    ) {
        Row(
            modifier = Modifier
                .requiredWidthIn(max = 250.dp)
                .background(Color.Black.copy(0.3f))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_music),
                contentDescription = "music",
                modifier = Modifier.size(18.dp),
            )
            Text(
                maxLines = 1,
                modifier = Modifier.basicMarquee(),
                text = text,
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }
}