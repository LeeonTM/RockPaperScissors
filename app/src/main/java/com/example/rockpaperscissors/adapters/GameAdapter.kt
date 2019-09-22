package com.example.rockpaperscissors.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rockpaperscissors.models.Game
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R


class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView
        }
    }
}