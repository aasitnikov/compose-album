package com.example.musicappcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun RoundButton(
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .background(color = color, shape = CircleShape)
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.Black.copy(alpha = 0.1f))
            ),
        contentAlignment = Alignment.Center,
        content = { content() },
    )
}

@Preview
@Composable
private fun SolidPreview() {
    MusicAppComposeTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFF2A5F79))
                .padding(32.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RoundButton(onClick = { }, color = Color(0xFFFFDD60)) {
                    Icon(painter = rememberVectorPainter(Icons.Filled.PlayArrow), contentDescription = null)
                }
                Spacer(Modifier.height(8.dp))
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium,
                    LocalContentColor provides Color.White,
                ) {
                    Text(
                        text = "Слушать",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TransparentPreview() {
    MusicAppComposeTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFF2A5F79))
                .padding(32.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RoundButton(onClick = { }, color = Color.White.copy(alpha = 0.1f)) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Outlined.Share),
                        tint = Color.White,
                        contentDescription = null,
                    )
                }
                Spacer(Modifier.height(8.dp))
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium,
                    LocalContentColor provides Color.White,
                ) {
                    Text(
                        text = "Поделиться",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
