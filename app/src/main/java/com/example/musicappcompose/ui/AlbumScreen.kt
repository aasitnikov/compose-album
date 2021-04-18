package com.example.musicappcompose.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicappcompose.Album
import com.example.musicappcompose.Overgrown
import com.example.musicappcompose.R
import com.example.musicappcompose.map
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun AlbumScreen(album: Album) {
    val lazyListState = rememberLazyListState()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize(), lazyListState) {
            item {
                Box {
                    val scrollFraction = lazyListState.firstItemOffsetFraction()
                    val scrimColor = MaterialTheme.colors.background.copy(alpha = scrollFraction)
                    Box(
                        Modifier.drawWithContent {
                            drawContent()
                            drawRect(scrimColor)
                        }
                    ) {
                        AlbumBackdrop(
                            album,
                            Color(0xff2A5F79),
                            lazyListState.firstItemOffsetPx(),
                            scrollFraction,
                            Modifier.fillMaxWidth(),
                        )
                    }
                    BottomSheetHat(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    )
                }
            }

            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(MaterialTheme.colors.background)
                )
            }

            items(album.tracks) { track ->
                TrackRow(track)
            }

            item {
                Spacer(Modifier.height(500.dp))
            }
        }

        TopBar(lazyListState, album.title)

        AnchoredBox(lazyListState, Modifier.fillMaxWidth()) {
            val fraction = lazyListState.firstItemOffsetFraction()
            val itemsAlpha = map(fraction, from = 0.5f..0.8f, to = 1f..0f).coerceIn(0f, 1f)
            ThreeButtons(itemsAlpha)
        }
    }
}

@Composable
private fun TopBar(state: LazyListState, albumTitle: String) {
    val offset = state.firstItemOffsetFraction()
    val alpha = map(offset, 0.78f..0.9f, 0f..0.96f).coerceIn(0f, 1f)
    Box(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .alpha(alpha)
            .background(if (MaterialTheme.colors.isLight) Color.White else Color.Black)
    ) {
        Text(
            text = albumTitle,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
    Box(Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 4.dp, start = 4.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 4.dp, end = 4.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}

@Composable
private fun LazyListState.firstItemOffsetPx(): Int {
    return if (firstVisibleItemIndex == 0) {
        firstVisibleItemScrollOffset
    } else {
        0
    }
}

@Composable
private fun LazyListState.firstItemOffsetFraction(): Float {
    return if (firstVisibleItemIndex == 0) {
        val firstItemInfo = layoutInfo.visibleItemsInfo.firstOrNull() ?: return 0f
        return -firstItemInfo.offset.toFloat() / firstItemInfo.size
    } else {
        1f
    }
}

@Composable
private fun ThreeButtons(itemsAlpha: Float) {
    Row {
        RoundButtonWithText(
            modifier = Modifier
                .weight(0.5f)
                .alpha(itemsAlpha),
            onClick = { /*TODO*/ },
            backgroundColor = Color.White.copy(alpha = 0.1f),
            rippleColor = Color.White,
            icon = painterResource(id = R.drawable.ic_share),
            iconTint = Color.White,
            text = "Поделиться"
        )
        RoundButtonWithText(
            onClick = { /*TODO*/ },
            backgroundColor = Color(0xFFFFDD60),
            rippleColor = Color(0xFFE64A19),
            icon = rememberVectorPainter(Icons.Filled.PlayArrow),
            iconTint = Color.Black,
            textAlpha = itemsAlpha,
            text = "Слушать"
        )
        RoundButtonWithText(
            modifier = Modifier
                .weight(0.5f)
                .alpha(itemsAlpha),
            onClick = { /*TODO*/ },
            backgroundColor = Color.White.copy(alpha = 0.1f),
            rippleColor = Color.White,
            icon = painterResource(id = R.drawable.ic_heart),
            iconTint = Color.White,
            text = "3 121"
        )
    }
}

@Preview
@Composable
private fun ThreeButtonsPreview() {
    MusicAppComposeTheme {
        Box(
            Modifier
                .background(Color(0XFF2A5F79))
                .padding(vertical = 32.dp)
        ) {
            ThreeButtons(1f)
        }
    }
}

@Composable
private fun AnchoredBox(
    state: LazyListState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 46.dp, bottom = 36.dp),
    content: @Composable BoxScope.() -> Unit
) {
    val backdrop = state.layoutInfo.visibleItemsInfo.firstOrNull()
    val backdropBottomY = if (backdrop == null) {
        780 // for preview
    } else {
        if (backdrop.index == 0) {
            backdrop.size + backdrop.offset
        } else {
            0
        }
    }

    var height by remember { mutableStateOf(0) }
    // TODO smooth easing instead of rough coerce
    val offset = (backdropBottomY - height).coerceAtLeast(0)

    Box(
        content = content,
        modifier = modifier
            .offset { IntOffset(0, offset) }
            .onSizeChanged { height = it.height }
            .padding(paddingValues)
    )
}

@Preview(widthDp = 360, heightDp = 740, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(widthDp = 360, heightDp = 740)
@Composable
private fun AlbumScreenPreview() {
    MusicAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            AlbumScreen(Overgrown)
        }
    }
}
