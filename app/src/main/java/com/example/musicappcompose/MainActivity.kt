package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappcompose.ui.AlbumBackdrop
import com.example.musicappcompose.ui.BottomSheetHat
import com.example.musicappcompose.ui.RoundButton
import com.example.musicappcompose.ui.Track
import com.example.musicappcompose.ui.TrackRow
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AlbumScreen(Overgrown)
                }
            }
        }
    }
}

@Composable
fun AlbumScreen(album: Album) {
    val state = rememberLazyListState()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize(), state) {
            item {
                Box {
                    val scrollOffset = if (state.firstVisibleItemIndex == 0) {
                        state.firstVisibleItemScrollOffset
                    } else {
                        0
                    }
                    AlbumBackdrop(
                        album,
                        Color(0xff2A5F79),
                        scrollOffset,
                        Modifier.fillMaxWidth(),
                    )
                    BottomSheetHat(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    )
                }
            }

            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(MaterialTheme.colors.background)
                )
            }

            items(album.tracks) { track ->
                TrackRow(track)
            }

            item {
                Spacer(Modifier.height(500.dp))
            }
        }

        AnchoredBox(state, Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                MusicAppComposeTheme(darkTheme = true) {
                    RoundButton(onClick = { }, color = Color(0xFFFFDD60)) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Filled.PlayArrow),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
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

@Composable
private fun AnchoredBox(
    state: LazyListState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 46.dp, bottom = 36.dp),
    content: @Composable BoxScope.() -> Unit
) {
    val backdrop = state.layoutInfo.visibleItemsInfo.firstOrNull()
    val backdropBottomY = if (backdrop != null && backdrop.index == 0) {
        backdrop.size + backdrop.offset
    } else {
        0
    }

    var height by remember { mutableStateOf(0) }
    // TODO smooth easing instead of rough coerce
    val offset = (backdropBottomY - height).coerceAtLeast(0)

    Box(
        content = content,
        modifier = modifier
            .offset { IntOffset(0, offset) }
            .onSizeChanged { height = it.height }
            .padding(paddingValues)
    )
}

data class Album(
    val title: String,
    val author: String,
    val year: String,
    val tracks: List<Track>,
)

val Overgrown = Album(
    "Overgrown",
    "James Blake",
    "2013",
    listOf(
        Track("Overgrown", 1, false),
        Track("I Am Sold", 2, false),
        Track("Life Round Here", 3, true),
        Track("Take A Fall For Me", 4, false),
        Track("Retrograde", 5, true),
        Track("DLM", 6, false),
        Track("Digital Lion", 7, false),
        Track("Voyeur", 8, false),
        Track("To The Last", 9, false),
        Track("Our Love Comes Back", 10, false),
    )
)
