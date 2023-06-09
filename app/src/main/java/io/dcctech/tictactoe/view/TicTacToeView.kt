/*
 * Copyright © 2022-2023, DCCTech
 */

package io.dcctech.tictactoe.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import io.dcctech.tictactoe.data.TicTacToeModel


class TicTacToeView : View {

    private val paintBg : Paint = Paint()
    private val paintLine : Paint = Paint()

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        paintBg.color = Color.GREEN
        paintBg.style = Paint.Style.FILL

        paintLine.color = Color.YELLOW
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBg)

        drawGameArea(canvas)
        drawPlayers(canvas)
    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // two horizontal lines
        canvas.drawLine(0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLine)
        canvas.drawLine(0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLine)

        // two vertical lines
        canvas.drawLine((width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLine)
        canvas.drawLine((2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLine)
    }

    private fun drawPlayers(canvas: Canvas?) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {

                    // draw a circle at the center of the field

                    // X coordinate: left side of the square + half width of the square
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas?.drawCircle(centerX, centerY, radius.toFloat(), paintLine)

                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas?.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLine)

                    canvas?.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {

            val tX = event.x.toInt() / (width / 3)
            val tY = event.y.toInt() / (height / 3)

            if (tX < 3 && tY < 3 && TicTacToeModel.getFieldContent(tX, tY) ==
                TicTacToeModel.EMPTY) {
                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())
                TicTacToeModel.changeNextPlayer()
                invalidate()
            }
        }

        return super.onTouchEvent(event)
    }

}