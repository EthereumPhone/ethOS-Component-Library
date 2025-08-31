package org.ethosmobile.components.library.background

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.animation.ExperimentalSharedTransitionApi
//import androidx.compose.animation.SharedTransitionLayout
//import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.core.ui.util.dgenBlack

//@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
//@Composable
//fun FullscreenImagePreview(
//    images: List<String>,
//    initialPage: Int,
//    onClose: () -> Unit,
//    primaryColor: Color,
//    sharedTransitionScope: SharedTransitionScope,
//    animatedContentScope: AnimatedContentScope
//) {
//    val pagerState = rememberPagerState(
//        initialPage = initialPage,
//        pageCount = { images.size }
//    )
//    var scale by remember { mutableStateOf(1f) }
//    var offsetX by remember { mutableStateOf(0f) }
//    var offsetY by remember { mutableStateOf(0f) }
//
//    LaunchedEffect(pagerState.currentPage) {
//        scale = 1f
//        offsetX = 0f
//        offsetY = 0f
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(dgenBlack)
//    ) {
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier.fillMaxSize(),
//            userScrollEnabled = scale == 1f
//        ) { page ->
//            val imageUrl = images[page]
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .pointerInput(Unit) {
//                        detectTransformGestures { _, pan, zoom, _ ->
//                            scale *= zoom
//                            if (scale > 1f) {
//                                offsetX += pan.x
//                                offsetY += pan.y
//                            } else {
//                                scale = 1f
//                                offsetX = 0f
//                                offsetY = 0f
//                            }
//                        }
//                    }
//                    .pointerInput(Unit) {
//                        detectTapGestures(
//                            onDoubleTap = {
//                                if (scale > 1f) {
//                                    scale = 1f
//                                    offsetX = 0f
//                                    offsetY = 0f
//                                } else {
//                                    scale = 2f
//                                }
//                            }
//                        )
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                with(sharedTransitionScope) {
//                    Image(
//                        painter = rememberAsyncImagePainter(imageUrl),
//                        contentDescription = "Full Image",
//                        modifier = Modifier
//                            .sharedElement(
//                                state = rememberSharedContentState(key = "image/$imageUrl"),
//                                animatedVisibilityScope = animatedContentScope
//                            )
//                            .fillMaxWidth()
//                            .graphicsLayer(
//                                scaleX = scale,
//                                scaleY = scale,
//                                translationX = offsetX,
//                                translationY = offsetY
//                            ),
//                        contentScale = ContentScale.Fit
//                    )
//                }
//            }
//        }
//
//        IconButton(
//            onClick = onClose,
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(16.dp)
//                .zIndex(1f)
//        ) {
//            Icon(
//                modifier = Modifier.size(32.dp),
//                imageVector = Icons.Default.Close,
//                contentDescription = "Close",
//                tint = primaryColor
//            )
//        }
//    }
//}