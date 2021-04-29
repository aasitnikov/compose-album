package com.example.musicappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
            val album = Album(
                "Overgrown",
                "James Blake",
                "2013",
                albumArtUrl = "poopa",
//                albumArtUrl = "https://impact89fm.org/wp-content/uploads/2019/02/KidsSeeGhosts.png",
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

            LaunchedEffect(Unit) {
                glide.pauseAllRequests()
                delay(800)
                glide.resumeRequests()
            }
            MusicAppComposeTheme {
                CompositionLocalProvider(LocalRequestManager provides glide) {
                    var isLoading by rememberSaveable { mutableStateOf(true) }
                    LaunchedEffect(Unit) {
                        delay(500)
                        isLoading = false
                    }
                    Surface(color = MaterialTheme.colors.background) {
                        AlbumScreen(
                            album.takeUnless { isLoading },
                            onBackClicked = { isLoading = !isLoading },
                            onMoreClicked = { isLoading = !isLoading },
                        )
                    }
                }
            }
        }
    }
}

val hello = Album(
    "POST HUMAN: SURVIVAL HORROR",
    "Bring Me The Horizon",
    "2020",
    albumArtUrl = "https://avatars.yandex.net/get-music-content/2424959/368a187d.a.12598856-1/400x400",
    artistArtUrl = "https://avatars.yandex.net/get-music-content/2480468/c26e06c2.p.43108/400x400",
    listOf(
        Track("Dear Diary,", 1, false),
        Track("Parasite Eve", 2, true),
        Track("Teardrops", 3, true),
        Track("Obey", 4, false),
        Track("Itch For The Cure (When Will We Be Free?)", 5, false),
        Track("Kingslayer", 6, false),
        Track("1x1", 7, false),
        Track("Ludens", 8, false),
        Track("One Day The Only Butterflies Left Will Be In Your Chest As You March Towards Your Death", 9, false),
    )
)


val Overgrown
    @Composable get() = Album(
        "Overgrown",
        "James Blake",
        "2013",
        albumArtUrl = "https://media.pitchfork.com/photos/59299ffcb1335d7bf1697f08/1:1/w_600/b7213934.jpg",
//        albumArtUrl = "https://impact89fm.org/wp-content/uploads/2019/02/KidsSeeGhosts.png",
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
