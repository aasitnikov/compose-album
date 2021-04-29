package com.example.musicappcompose.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappcompose.Album
import com.example.musicappcompose.Overgrown
import com.example.musicappcompose.map
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme
import kotlinx.coroutines.delay


@Composable
fun AlbumScreen2(album: Album?) {
    val lazyListState = rememberLazyListState()

    Box(Modifier.fillMaxSize()) {
        if (album == null) {
            AlbumLoadingScreen(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .background(color = albumBackgroundColor)
            )

        } else {
            Box(Modifier.fillMaxSize()) {
                LazyColumn(Modifier.fillMaxSize(), lazyListState) {
                    item {
                        Box {
                            val scrollFraction = lazyListState.firstItemOffsetFraction()
                            val scrimColor = MaterialTheme.colors.background.copy(alpha = scrollFraction)
                            Box(
                                Modifier.drawWithContent {
                                    drawContent()
                                    drawRect(scrimColor)
                                }
                            ) {
                                AlbumBackdrop(
                                    album,
                                    lazyListState.firstItemOffsetPx(),
                                    scrollFraction,
                                    Modifier.fillMaxWidth(),
                                    Color(0xff2A5F79),
                                )
                            }
                            BottomSheetHat(
                                Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                            )
                        }
                    }

                    item {
                        Box(Modifier.height(24.dp))
                    }

                    items(album.tracks) { track ->
                        TrackRow(track)
                    }

                    item {
                        Box(Modifier.height(300.dp))
                    }
                }
                AnchoredBox(lazyListState, Modifier.fillMaxWidth()) {
                    val fraction = lazyListState.firstItemOffsetFraction()
                    val itemsAlpha = map(fraction, from = 0.5f..0.8f, to = 1f..0f).coerceIn(0f, 1f)
                    ThreeButtons(itemsAlpha, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }
}

@Composable
fun RoundButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.background(backgroundColor, CircleShape),
        content = content
    )
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 360)
@Preview(widthDp = 360)
@Composable
fun AlbumScreen2Preview() {
    isInEditMode = true

    val someAlbum = Overgrown
    val album = produceState<Album?>(null) {
        delay(5000)
        value = someAlbum
    }

    MusicAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            AlbumScreen2(album.value)
        }
    }

//    RoundButton(onClick = { /*TODO*/ }, backgroundColor = ) {
//
//    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 360)
@Composable
fun LoadingStatePreview() {
    MusicAppComposeTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            AlbumLoadingScreen(Modifier.fillMaxWidth())
        }
    }
}