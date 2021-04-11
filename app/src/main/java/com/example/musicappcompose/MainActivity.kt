package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        items(album.tracks) { track ->
            TrackRow(track)
        }
    }
}

data class Album(
    val tracks: List<Track>,
)

val Overgrown = Album(
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