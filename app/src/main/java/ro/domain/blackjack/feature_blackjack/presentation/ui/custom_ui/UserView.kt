package ro.domain.blackjack.feature_blackjack.presentation.ui.custom_ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.domain.blackjack.R
import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.presentation.game.adapter.PlayerCardsAdapter

class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val recyclerView: RecyclerView
    private val points: TextView
    private val result: ImageView

    init {
        val view = inflate(context, R.layout.user_view_layout, this)

        view.apply {
            points = findViewById(R.id.points)
            recyclerView = findViewById(R.id.rv_cards)
            result = findViewById(R.id.result)
        }
    }

    fun setView(player: Player) {
        setAdapter(player)
        setPoints(player)
        showCardsState(player.playerState)
    }

    private fun setAdapter(player: Player) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PlayerCardsAdapter(player.playerState != Player.PlayerState.Wait, player.drawnCardsList, context)
        }
    }

    private fun setPoints(player: Player) {
        points.apply {
            visibility = if (player.playerState != Player.PlayerState.Wait) View.VISIBLE else View.GONE
            text = player.totalPoints.toString()
        }
    }

    private fun showCardsState(playerState: Player.PlayerState) {
        when (playerState) {
            Player.PlayerState.Win -> {
                result.setImageResource(R.drawable.winner)
                result.visibility = View.VISIBLE
            }
            Player.PlayerState.Lost -> {
                result.setImageResource(R.drawable.lost)
                result.visibility = View.VISIBLE

            }
            else -> result.visibility = View.INVISIBLE
        }
    }
}