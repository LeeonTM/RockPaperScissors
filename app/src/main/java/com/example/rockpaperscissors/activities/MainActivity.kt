package com.example.rockpaperscissors.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.enums.*
import com.example.rockpaperscissors.models.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

const val VIEW_HISTORY_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository

    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameRepository = GameRepository(this)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_show_history -> {
                var intent = Intent(this, HistoryActivity::class.java)
                startActivityForResult(intent, VIEW_HISTORY_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        supportActionBar?.title = "Rock, Paper, Scissors Kotlin"

        btnRock.setOnClickListener{generateGame(Move.ROCK)}
        btnPaper.setOnClickListener{generateGame(Move.PAPER)}
        btnScissors.setOnClickListener{generateGame(Move.SCISSORS)}
    }

    private fun generateGame(playerMove: Move){
        mainScope.launch {
            var game = Game (
                playerMove = playerMove,
                computerMove = (0..2).random().toEnum<Move>(),
                date = Calendar.getInstance().time
            )

            game.result = getGameResult(game.playerMove, game.computerMove)

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }

            setGameResult(game)
        }
    }

    private fun getGameResult(playerMove: Move, computerMove: Move): Result {
        return when (playerMove) {
            computerMove -> Result.DRAW
            else -> {
                if (playerMove == Move.ROCK && computerMove == Move.PAPER) {
                    Result.LOSS
                } else if (playerMove == Move.SCISSORS && computerMove == Move.ROCK) {
                    Result.LOSS
                } else if (playerMove == Move.PAPER && computerMove == Move.SCISSORS) {
                    Result.LOSS
                } else {
                    Result.WIN
                }
            }
        }
    }

    private fun setGameResult(game: Game) {
        when(game.result) {
            Result.WIN -> lblResult.text = "You win!"
            Result.DRAW -> lblResult.text = "Draw!"
            Result.LOSS -> lblResult.text = "Computer wins!"
        }

        when(game.playerMove) {
            Move.SCISSORS -> imgPlayerMove.setImageResource(R.drawable.scissors)
            Move.ROCK -> imgPlayerMove.setImageResource(R.drawable.rock)
            Move.PAPER -> imgPlayerMove.setImageResource(R.drawable.paper)
        }

        when(game.computerMove) {
            Move.SCISSORS -> imgComputerMove.setImageResource(R.drawable.scissors)
            Move.ROCK -> imgComputerMove.setImageResource(R.drawable.rock)
            Move.PAPER -> imgComputerMove.setImageResource(R.drawable.paper)
        }
    }

    private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]
}
