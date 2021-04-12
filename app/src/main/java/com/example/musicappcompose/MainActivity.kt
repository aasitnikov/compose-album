package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicappcompose.ui.AlbumBackdrop
import com.example.musicappcompose.ui.BottomSheetHat
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
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Box {
                AlbumBackdrop(
                    album,
                    Color(0xff2A5F79),
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
            Box(Modifier.fillMaxWidth().height(24.dp).background(MaterialTheme.colors.background))
        }

        items(album.tracks) { track ->
            TrackRow(track)
        }
    }
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
