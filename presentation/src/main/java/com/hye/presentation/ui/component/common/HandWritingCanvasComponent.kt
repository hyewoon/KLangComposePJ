package com.hye.presentation.ui.component.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.hye.presentation.model.DrawingPath


@Composable
fun HandWritingCanvasComponent(
    completedPaths: List<DrawingPath>,
    currentPath: DrawingPath,
    onPathsChanged: (List<DrawingPath>) -> Unit,
    onCurrentPathChanged: (DrawingPath) -> Unit,
    watermarkText: String? = null,
    watermarkAlpha: Float = 0.3f,
    watermarkTextSize: Float = 80f,
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset -> //시작점
                    println("🎯 터치 시작: (${offset.x}, ${offset.y})")
                    val newPath = DrawingPath(
                        path = Path().apply {
                            moveTo(offset.x, offset.y)
                        },
                        color = Color.Blue,
                        strokeWidth = 15f
                    )
                    onCurrentPathChanged(newPath)
                    println("📝 새 Path 생성: 시작점 (${offset.x}, ${offset.y})")
                },
                onDrag = { change, dragAmount -> //중간점
                    println("✋ 드래그: change(${change.position.x}, ${change.position.y}), amount(${dragAmount.x}, ${dragAmount.y})")
                    val newPath = Path().apply {
                        addPath(currentPath.path)
                        lineTo(change.position.x, change.position.y)
                        //relativeLineTo(dragAmount.x, dragAmount.y)
                    }
                    onCurrentPathChanged(currentPath.copy(path = newPath))
                    println("🖊️ Path 업데이트: 새 점 (${change.position.x}, ${change.position.y})")
                },
                onDragEnd = { //끝점
                    println("🏁 터치 종료")
                    onPathsChanged(completedPaths + currentPath)
                    onCurrentPathChanged(
                        DrawingPath(
                            Path(),
                            Color.Blue,
                            15f
                        )
                    )
                    println("💾 Path 저장 완료")

                }
            )
        }
    ) {
        // 🔍 디버깅: Canvas 크기 확인
        val canvasWidth = size.width
        val canvasHeight = size.height
        println("🎨 Canvas 실제 크기: ${canvasWidth} x ${canvasHeight}")
        drawRect(
            color = Color.LightGray,
            size = size
        )


        // 🔍 테스트: 대각선 그어보기 (디버깅용)
        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = canvasWidth, y = canvasHeight),
            color = Color.Red,
            strokeWidth = 3f
        )

        //워터마크 그리기
        watermarkText?.let { text ->
            val textStyle = TextStyle(
                fontSize =watermarkTextSize.sp,  // 적절한 크기로 조정
                color = Color.Gray.copy(alpha = watermarkAlpha),
                fontWeight = FontWeight.Bold
            )
            val textLayoutResult = textMeasurer.measure(text, textStyle)

            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = textStyle,
                topLeft = Offset(
                    x = (size.width - textLayoutResult.size.width) /2,
                    y = (size.height - textLayoutResult.size.height) /2
                )
            )
        }

        //획들 그리기
        completedPaths.forEachIndexed{index, path ->
            println("🖌️ 완성된 획 $index 그리기 시도")
            drawPath(
                path = path.path,
                color = Color.Green,
                style = Stroke(
                    width = 15f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )

        }
        println("✏️ 현재 획 그리기 시도")
        drawPath(
            path = currentPath.path,
            color = Color.Blue,
            style = Stroke(
                width = 15f,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }

}