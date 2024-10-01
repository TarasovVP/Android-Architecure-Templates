@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package com.vnteam.architecturetemplates

import kotlin.OptIn
import org.jetbrains.compose.resources.FontResource

private object CommonMainFont0 {
  public val robotomono_italic: FontResource by 
      lazy { init_robotomono_italic() }

  public val robotomono_regular: FontResource by 
      lazy { init_robotomono_regular() }
}

public val Res.font.robotomono_italic: FontResource
  get() = CommonMainFont0.robotomono_italic

private fun init_robotomono_italic(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:robotomono_italic",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.vnteam.architecturetemplates/font/robotomono_italic.ttf", -1, -1),
    )
)

public val Res.font.robotomono_regular: FontResource
  get() = CommonMainFont0.robotomono_regular

private fun init_robotomono_regular(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:robotomono_regular",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/com.vnteam.architecturetemplates/font/robotomono_regular.ttf", -1, -1),
    )
)
