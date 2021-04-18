package com.example.musicappcompose.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun AlbumLoadingScreen(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    val opacity by infiniteTransition.animateFloat(
        initialValue = 0.05f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color = MaterialTheme.colors.onBackground

    Column(modifier = modifier.alpha(opacity), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .padding(top = 40.dp)
                .size(192.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
        )

        Box(
            Modifier
                .padding(top = 18.dp)
                .size(188.dp, 22.dp)
                .background(color = color, shape = CircleShape)
        )

        Row(
            Modifier
                .width((64 * 3 + 40 * 2).dp)
                .padding(top = 54.dp, bottom = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(3) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        Modifier
                            .size(64.dp)
                            .background(color = color, shape = CircleShape)
                    )
                    Box(
                        Modifier
                            .padding(top = 12.dp)
                            .size(36.dp, 8.dp)
                            .background(color = color, shape = CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun AlbumLoadingScreenPreview() {
    MusicAppComposeTheme {
        Surface(color = Color(0xFF141414)) {
            Box(Modifier.width(360.dp)) {
                AlbumLoadingScreen(Modifier.align(Alignment.Center))
            }
        }
    }
}