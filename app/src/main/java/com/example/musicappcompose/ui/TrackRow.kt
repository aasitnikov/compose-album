package com.example.musicappcompose.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappcompose.R
import com.example.musicappcompose.Track
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun TrackRow(
    track: Track,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .height(52.dp)
            .fillMaxWidth()
            .clickable(onClick = { /*TODO*/ }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.width(20.dp), contentAlignment = Alignment.Center) {
            if (track.isPopular) {
                Image(
                    painterResource(id = R.drawable.ic_lightning),
                    contentDescription = null,
                    Modifier
                        .padding(start = 4.dp)
                        .size(8.dp, 12.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    alpha = ContentAlpha.medium,
                )
            }
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                track.position.toString(),
                Modifier.size(16.dp),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.width(8.dp))

        Text(
            track.title,
            Modifier.weight(1f),
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 360)
@Preview(widthDp = 360)
@Composable
private fun TrackPreview() {
    MusicAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            val track = Track("Bohemian rhapsody", 4, true)
            TrackRow(track)
        }
    }
}
