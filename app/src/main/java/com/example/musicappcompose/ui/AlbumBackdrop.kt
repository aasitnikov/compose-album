package com.example.musicappcompose.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappcompose.Album
import com.example.musicappcompose.Overgrown
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun AlbumBackdrop(
    album: Album,
    backgroundColor: Color,
    scrollPx: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = backgroundColor,
        contentColor = Color.White,
        modifier = modifier.height(450.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(36.dp))
            Box(
                Modifier
                    .offset { IntOffset(0, scrollPx) }
                    .size(192.dp)
                    .background(Color.Black)
            )

            Spacer(Modifier.height(16.dp))
            Text(album.title, fontSize = 24.sp, fontWeight = FontWeight.Medium)

            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(Color.Black, shape = CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("${album.author} · ${album.year}", fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun AlbumBackdropPreview() {
    MusicAppComposeTheme {
        AlbumBackdrop(
            album = Overgrown,
            backgroundColor = Color(0xff2A5F79),
            scrollPx = 0,
            modifier = Modifier.width(360.dp),
        )
    }
}
