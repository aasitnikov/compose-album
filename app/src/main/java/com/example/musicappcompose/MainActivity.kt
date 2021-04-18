package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.musicappcompose.ui.AlbumScreen
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
