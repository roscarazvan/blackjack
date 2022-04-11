package ro.domain.blackjack.feature_blackjack.domain.model

data class Card(
    val suit: Suit,
    val value: String
) {
    companion object {
        const val ACE = "A"
        const val KING = "K"
        const val QUEEN = "Q"
        const val JUVET = "J"
    }
}
