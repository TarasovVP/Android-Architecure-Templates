package com.vnteam.architecturetemplates.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.vnteam.architecturetemplates.resources.default_padding
import com.vnteam.architecturetemplates.resources.medium_padding
import com.vnteam.architecturetemplates.resources.small_padding

val Shapes =
    Shapes(
        small = RoundedCornerShape(small_padding.size.value),
        medium = RoundedCornerShape(medium_padding.size.value),
        large = RoundedCornerShape(default_padding.size.value),
    )
