package com.example.stravel.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun HomeIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "home",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9.542f, 32.125f)
                horizontalLineToRelative(5.75f)
                verticalLineToRelative(-10.25f)
                horizontalLineToRelative(9.416f)
                verticalLineToRelative(10.25f)
                horizontalLineToRelative(5.75f)
                verticalLineTo(16.417f)
                lineTo(20f, 8.583f)
                lineTo(9.542f, 16.417f)
                close()
                moveToRelative(0f, 2.625f)
                quadToRelative(-1.084f, 0f, -1.855f, -0.771f)
                quadToRelative(-0.77f, -0.771f, -0.77f, -1.854f)
                verticalLineTo(16.417f)
                quadToRelative(0f, -0.625f, 0.271f, -1.188f)
                quadToRelative(0.27f, -0.562f, 0.77f, -0.937f)
                lineToRelative(10.459f, -7.834f)
                quadToRelative(0.375f, -0.25f, 0.771f, -0.375f)
                quadToRelative(0.395f, -0.125f, 0.812f, -0.125f)
                quadToRelative(0.417f, 0f, 0.812f, 0.125f)
                quadToRelative(0.396f, 0.125f, 0.771f, 0.375f)
                lineToRelative(10.459f, 7.834f)
                quadToRelative(0.5f, 0.375f, 0.77f, 0.937f)
                quadToRelative(0.271f, 0.563f, 0.271f, 1.188f)
                verticalLineToRelative(15.708f)
                quadToRelative(0f, 1.083f, -0.771f, 1.854f)
                quadToRelative(-0.77f, 0.771f, -1.854f, 0.771f)
                horizontalLineToRelative(-8.375f)
                verticalLineTo(24.5f)
                horizontalLineToRelative(-4.166f)
                verticalLineToRelative(10.25f)
                close()
                moveTo(20f, 20.333f)
                close()
            }
        }.build()
    }
}