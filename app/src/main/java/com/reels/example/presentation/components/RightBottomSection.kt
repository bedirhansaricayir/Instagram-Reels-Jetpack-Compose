package com.reels.example.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.reels.example.R
import com.reels.example.core.util.noRippleClickable
import com.reels.example.domain.model.VideoInfo

@Composable
fun RightBottomSection(
    modifier: Modifier = Modifier,
    videoInfo: VideoInfo,
    onFavoriteClicked: () -> Unit,
    onCommentClicked: () -> Unit,
) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconWithText(
            icon = if (videoInfo.isLiked) R.drawable.ic_filled_favorite else R.drawable.ic_outlined_favorite,
            text = videoInfo.likes,
            iconColor = if (videoInfo.isLiked) Color.Red else Color.White,
            onClicked = onFavoriteClicked
        )
        IconWithText(
            icon = R.drawable.ic_outlined_comment,
            text = videoInfo.commentCount,
            iconColor = Color.White,
            onClicked = onCommentClicked
        )
        IconWithText(icon = R.drawable.ic_dm, text = videoInfo.send, iconColor = Color.White)
        Icon(
            modifier = Modifier.padding(vertical = 8.dp),
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "",
            tint = Color.White
        )
        RoundedSoundImage(image = videoInfo.soundImage)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconColor: Color,
    text: String,
    onClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .noRippleClickable { onClicked() },
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = iconColor
        )
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun RoundedSoundImage(
    modifier: Modifier = Modifier,
    image: String
) {
    AsyncImage(
        model = image,
        modifier = modifier
            .size(28.dp)
            .background(color = Color.White, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            ),
        contentDescription = null
    )
}