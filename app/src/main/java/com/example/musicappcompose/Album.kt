package com.example.musicappcompose

data class Album(
    val title: String,
    val author: String,
    val year: String,
    val albumArtUrl: String,
    val artistArtUrl: String,
    val tracks: List<Track>,
)
