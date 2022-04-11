package ro.domain.blackjack.feature_blackjack.domain.model

import ro.domain.blackjack.R

enum class Suit {
    SPADES {
        override fun getColor() = R.color.black
        override fun getDrawable() = R.drawable.spade
    },
    CLUBS {
        override fun getColor() = R.color.black
        override fun getDrawable() = R.drawable.clubs
    },
    DIAMONDS {
        override fun getColor() = R.color.red
        override fun getDrawable() = R.drawable.diamond
    },
    HEARTS {
        override fun getColor() = R.color.red
        override fun getDrawable() = R.drawable.heart
    };

    abstract fun getColor(): Int
    abstract fun getDrawable(): Int
}
