package com.example.rockpaperscissors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.rockpaperscissors.models.Game
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.item_game.view.*
import com.example.rockpaperscissors.enums.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            var formatter = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale("NL"))
            itemView.lblDate.text = formatter.format(game.date)

            setMoveImage(game.computerMove, itemView.imgComputerMove)
            setMoveImage(game.playerMove, itemView.imgPlayerMove)

            when(game.result) {
                Result.WIN -> itemView.lblResult.text = "You win!"
                Result.DRAW -> itemView.lblResult.text = "Draw!"
                Result.LOSS -> itemView.lblResult.text = "Computer wins!"
            }
        }
    }

    private fun setMoveImage(move: Move, view : ImageView) {
        when(move) {
            Move.PAPER -> view.setImageResource(R.drawable.paper)
            Move.ROCK -> view.setImageResource(R.drawable.rock)
            Move.SCISSORS -> view.setImageResource(R.drawable.scissors)
        }
    }
}