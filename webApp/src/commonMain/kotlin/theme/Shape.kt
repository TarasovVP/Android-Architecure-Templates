package theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import resources.large_padding
import resources.medium_padding
import resources.small_padding

val Shapes = Shapes(
    small = RoundedCornerShape(small_padding.size.value),
    medium = RoundedCornerShape(medium_padding.size.value),
    large = RoundedCornerShape(large_padding.size.value)
)