package ro.domain.blackjack.feature_blackjack.presentation.game.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.domain.blackjack.R
import ro.domain.blackjack.feature_blackjack.domain.model.Card

class PlayerCardsAdapter(
    private val showCards: Boolean = false,
    private val userCards: List<Card>,
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CardTypes.VISIBLE_CARDS -> {
                val view = LayoutInflater.from(context).inflate(R.layout.card_view_layout, parent, false)
                PlayerCardViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.back_card_layout, parent, false)
                PlayerBackCardHolder(view)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (showCards) CardTypes.VISIBLE_CARDS else CardTypes.HIDDEN_CARDS
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            CardTypes.VISIBLE_CARDS -> (holder as PlayerCardViewHolder).apply {
                userCards[position].apply {
                    value.apply {
                        bottomText.text = this
                        topText.text = this
                    }
                    suit.getColor().apply {
                        bottomText.setTextColor(this)
                        topText.setTextColor(this)
                    }
                    suit.getDrawable().apply {
                        topImage.setImageResource(this)
                        bottomImage.setImageResource(this)
                        centerImage.setImageResource(this)
                    }
                }
            }
        }
    }

    override fun getItemCount() = userCards.size

    class PlayerCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topText: TextView = view.findViewById(R.id.card_number_top)
        val topImage: ImageView = view.findViewById(R.id.card_suit_top)
        val centerImage: ImageView = view.findViewById(R.id.card_center_image)
        val bottomText: TextView = view.findViewById(R.id.card_number_bottom)
        val bottomImage: ImageView = view.findViewById(R.id.card_suit_bottom)
    }

    class PlayerBackCardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val backCard = view
    }

    object CardTypes {
        const val VISIBLE_CARDS = 0
        const val HIDDEN_CARDS = 1
    }

}