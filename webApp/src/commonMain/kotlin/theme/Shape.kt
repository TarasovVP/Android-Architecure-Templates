package theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import resources.large_padding
import resources.medium_padding
import resources.small_padding

val Shapes = Shapes(
    small = RoundedCornerShape(small_padding.margin.value),
    medium = RoundedCornerShape(medium_padding.margin.value),
    large = RoundedCornerShape(large_padding.margin.value)
)