package com.example.musicappcompose.ui

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.example.musicappcompose.Album
import com.example.musicappcompose.Overgrown
import com.example.musicappcompose.map
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme
import com.google.accompanist.glide.GlideImage
import com.google.accompanist.imageloading.ImageLoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AlbumBackdrop(
    album: Album,
    scrollPx: Int,
    scrollFraction: Float,
    modifier: Modifier = Modifier,
    backgroundColorOverride: Color = Color.Unspecified, // for preview
) {
    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    val backgroundColor by if (backgroundColorOverride.isSpecified) {
        remember { mutableStateOf(backgroundColorOverride) }
    } else {
        val color = backgroundColorFromBitmap(bitmap)
        animateColorAsState(color.takeIf { it.isSpecified } ?: albumBackgroundColor)
    }

    Surface(
        color = backgroundColor,
        contentColor = Color.White,
        modifier = modifier.height(450.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(36.dp))

            GlideImage(
                data = album.albumArtUrl,
                modifier = Modifier
                    .offset { IntOffset(0, scrollPx) }
                    .size(192.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentDescription = null,
                fadeIn = true,
                onRequestCompleted = { loadState ->
                    if (loadState is ImageLoadState.Success) {
                        bitmap = (loadState.painter as BitmapPainter).asAndroidBitmap()
                    }
                },
                loading = {
                    Box(
                        Modifier
                            .size(192.dp)
                            .background(
                                shape = RoundedCornerShape(4.dp),
                                color = Color.White.copy(alpha = if (isSystemInDarkTheme()) 0.05f else 0.1f)
                            )
                    )
                }
            )

            val titleAlpha = map(scrollFraction, from = 0.2f..0.5f, to = 1f..0f).coerceIn(0f, 1f)
            val alphaModifier = Modifier.graphicsLayer(alpha = titleAlpha)

            Spacer(Modifier.height(16.dp))
            Text(album.title, fontSize = 24.sp, fontWeight = FontWeight.Medium, modifier = alphaModifier)

            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = alphaModifier) {
                GlideImage(
                    album.artistArtUrl,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    contentDescription = null,
                    fadeIn = true,
                    loading = {
                        Box(
                            Modifier
                                .size(24.dp)
                                .background(
                                    shape = CircleShape,
                                    color = Color.White.copy(alpha = if (isSystemInDarkTheme()) 0.05f else 0.1f)
                                )
                        )
                    }
                )
                Spacer(Modifier.width(8.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("${album.author} · ${album.year}", fontSize = 14.sp)
                }
            }
        }
    }
}

private fun BitmapPainter.asAndroidBitmap(): Bitmap {
    return this.javaClass.declaredFields
        .first { it.name == "image" }
        .apply { isAccessible = true }
        .let { field -> field.get(this) as ImageBitmap }
        .asAndroidBitmap()
}

@Composable
private fun backgroundColorFromBitmap(bitmap: Bitmap?): Color {
    if (bitmap == null) return Color.Unspecified

    val palette: Palette? by produceState<Palette?>(initialValue = null, key1 = bitmap) {
        value = withContext(Dispatchers.IO) { Palette.from(bitmap).generate() }
    }

    palette.apply {
        if (this == null) return Color.Unspecified
        val swatch = darkMutedSwatch ?: darkVibrantSwatch ?: mutedSwatch ?: vibrantSwatch
        return if (swatch != null) {
            Color(swatch.rgb)
        } else {
            Color.Unspecified
        }
    }
}

private val albumBackgroundColor: Color
    @Composable get() = if (MaterialTheme.colors.isLight) Color(0xFF616161) else Color(0xff141414)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun AlbumBackdropPreview() {
    MusicAppComposeTheme {
        AlbumBackdrop(
            album = Overgrown,
            backgroundColorOverride = Color(0xff2A5F79),
            scrollPx = 0,
            scrollFraction = 0f,
            modifier = Modifier.width(360.dp),
        )
    }
}
