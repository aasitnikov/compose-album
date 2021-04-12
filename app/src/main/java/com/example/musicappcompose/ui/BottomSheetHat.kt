package com.example.musicappcompose.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun BottomSheetHat(modifier: Modifier = Modifier) {
    Box(
        modifier
            .height(16.dp)
            .width(96.dp)
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Box(
            Modifier
                .size(60.dp, 4.dp)
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun BottomSheetHatPreview() {
    MusicAppComposeTheme {
        BottomSheetHat(Modifier.width(240.dp))
    }
}
