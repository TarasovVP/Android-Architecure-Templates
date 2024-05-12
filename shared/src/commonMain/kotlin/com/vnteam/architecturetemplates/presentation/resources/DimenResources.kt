package com.vnteam.architecturetemplates.presentation.resources

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class Dimens(val size: Dp = 0.dp, val textSize: TextUnit = 0.sp)

val small_padding = Dimens(size = 4.dp)
val medium_padding = Dimens(size = 8.dp)
val large_padding = Dimens(size = 16.dp)
val default_text_size = Dimens(textSize = 16.sp)

private val avatar_size = Dimens(size = 50.dp)

val LocalAvatarSize = staticCompositionLocalOf { avatar_size }
val LocalSmallPadding = staticCompositionLocalOf { small_padding }
val LocalMediumPadding = staticCompositionLocalOf { medium_padding }
val LocalLargePadding = staticCompositionLocalOf { large_padding }
val LocalDefaultTextSize = staticCompositionLocalOf { default_text_size }