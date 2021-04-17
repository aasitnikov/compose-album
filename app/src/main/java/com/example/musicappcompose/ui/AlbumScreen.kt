package com.example.musicappcompose.ui

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.musicappcompose.Album
import com.example.musicappcompose.R
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

@Composable
fun AlbumScreen(album: Album) {
    val state = rememberLazyListState()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize(), state) {
            item {
                Box {
                    val scrollFraction = state.firstItemOffsetFraction()
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
                            state.firstItemOffsetPx(),
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

        AnchoredBox(state, Modifier.fillMaxWidth()) {
            ThreeButtons()
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
        0f
    }
}

@Composable
private fun ThreeButtons() {
    Row {
        RoundButtonWithText(
            modifier = Modifier.weight(0.5f),
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
            text = "Слушать"
        )
        RoundButtonWithText(
            modifier = Modifier.weight(0.5f),
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
            ThreeButtons()
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
    val backdropBottomY = if (backdrop != null && backdrop.index == 0) {
        backdrop.size + backdrop.offset
    } else {
        0
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
