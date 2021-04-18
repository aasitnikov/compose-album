package com.example.musicappcompose

data class Album(
    val title: String,
    val author: String,
    val year: String,
    val tracks: List<Track>,
)
