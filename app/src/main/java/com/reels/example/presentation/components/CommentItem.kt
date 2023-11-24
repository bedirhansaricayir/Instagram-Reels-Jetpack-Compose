package com.reels.example.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.reels.example.core.util.noRippleClickable
import com.reels.example.domain.model.Comment

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        UserImage(
            image = comment.userImage
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = comment.userTitle,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier,
                    text = comment.commentTime,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            Text(
                modifier = Modifier,
                text = comment.comment,
                color = Color.Black.copy(0.8f)
            )
            Text(
                modifier = Modifier,
                text = "Yanıtla",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier,
                text = comment.likeCount,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CommentItemWithConstraint(
    modifier: Modifier = Modifier,
    model: Comment,
    onLikeButtonClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val (userImage, userTitle, commentTime, comment, yanitla, likeButton, likeCount) = createRefs()
        UserImage(
            image = model.userImage,
            modifier = Modifier.constrainAs(userImage) {
                top.linkTo(parent.top, margin = 6.dp)
                start.linkTo(parent.start)
            },
        )
        Text(
            text = model.userTitle,
            color = Color.Black,
            modifier = Modifier.constrainAs(userTitle) {
                top.linkTo(parent.top)
                start.linkTo(userImage.end, margin = 8.dp)
            }
        )
        Text(
            text = model.commentTime,
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.constrainAs(commentTime) {
                top.linkTo(parent.top)
                start.linkTo(userTitle.end, margin = 8.dp)
            }
        )
        Text(
            text = model.comment,
            color = Color.Black.copy(0.8f),
            modifier = Modifier
                .constrainAs(comment) {
                    top.linkTo(userTitle.bottom, margin = 4.dp)
                    start.linkTo(userTitle.start)
                }
                .fillMaxWidth(0.75f),

            )
        Text(
            text = "Yanıtla",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.constrainAs(yanitla) {
                top.linkTo(comment.bottom, margin = 4.dp)
                start.linkTo(comment.start)
            }
        )
        Icon(
            imageVector = if (model.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "",
            tint = if (model.isLiked) Color.Red else Color.Gray,
            modifier = Modifier
                .size(20.dp)
                .constrainAs(likeButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, margin = 8.dp)
                }
                .noRippleClickable { onLikeButtonClick.invoke() },

            )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (model.isLiked) "${model.likeCount.toInt()+1}" else model.likeCount,
            color = Color.Gray,
            modifier = Modifier.constrainAs(likeCount) {
                top.linkTo(likeButton.bottom)
                start.linkTo(likeButton.start)
                end.linkTo(likeButton.end)
            }
        )
    }

}

@Preview(
    showBackground = true,
    apiLevel = 33,
    device = "id:Nexus S"
)
@Composable
fun PreviewCommentItem() {
    CommentItemWithConstraint(model = Comment(
        userImage = "",
        userTitle = "filmMatik",
        commentTime = "1h",
        comment = "Big buck bunny tells the story of a giant rabbit with a heart bigger than himself.",
        likeCount = "48",
        isLiked = true,
    ),
        onLikeButtonClick = {}
    )
}

@Preview(
    showBackground = true,
    apiLevel = 30,
    device = "id:pixel_7_pro"
)
@Composable
fun PreviewCommentItemWithoutConstraint() {
    CommentItem(
        comment = Comment(
            userImage = "",
            userTitle = "filmMatik",
            commentTime = "1h",
            comment = "Big buck bunny tells the story of a giant rabbit with a heart bigger than himself.",
            likeCount = "48",
            isLiked = false
        ),
    )
}
