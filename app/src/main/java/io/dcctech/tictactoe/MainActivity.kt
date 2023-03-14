/*
 * Copyright © 2022-2023, DCCTech
 */

package io.dcctech.tictactoe

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dcctech.tictactoe.R
import io.dcctech.tictactoe.view.TicTacToeView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ticTacToeView = findViewById<TicTacToeView>(R.id.ticTacToeView)

        (findViewById<Button>(R.id.btnRestart)).setOnClickListener {
            ticTacToeView.resetGame()
        }


    }

    //Extension functions
    private fun TicTacToeView.resetGame() {
        io.dcctech.tictactoe.data.TicTacToeModel.resetModel()
        invalidate()
    }
}
