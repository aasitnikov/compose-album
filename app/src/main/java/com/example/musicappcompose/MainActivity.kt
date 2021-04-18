package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import com.example.musicappcompose.ui.AlbumScreen
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme
import com.google.accompanist.glide.LocalRequestManager
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val glide = Glide.with(applicationContext)
        setContent {
            LaunchedEffect(Unit) {
                glide.pauseAllRequests()
                delay(3000)
                glide.resumeRequests()
            }
            MusicAppComposeTheme {
                CompositionLocalProvider(LocalRequestManager provides glide) {
                    var isLoading by rememberSaveable { mutableStateOf(true) }
                    Surface(color = MaterialTheme.colors.background) {
                        AlbumScreen(
                            Overgrown.takeUnless { isLoading },
                            onBackClicked = { isLoading = !isLoading },
                            onMoreClicked = { isLoading = !isLoading },
                        )
                    }
                }
            }
        }
    }
}

val Overgrown = Album(
    "Overgrown",
    "James Blake",
    "2013",
    albumArtUrl = "https://media.pitchfork.com/photos/59299ffcb1335d7bf1697f08/1:1/w_600/b7213934.jpg",
    artistArtUrl = "https://s.err.ee/photo/crop/2020/02/11/742810he0f6t4.jpg",
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
