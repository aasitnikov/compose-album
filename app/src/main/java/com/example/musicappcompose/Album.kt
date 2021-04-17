package com.example.musicappcompose

import com.example.musicappcompose.ui.Track

data class Album(
    val title: String,
    val author: String,
    val year: String,
    val tracks: List<Track>,
)
