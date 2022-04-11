package ro.domain.blackjack.feature_blackjack.domain.model

data class Player(
    val drawnCardsList: MutableList<Card>,
    var totalPoints: Int = 0,
    var playerState: PlayerState = PlayerState.CanRequestCard
) {

    sealed class PlayerState {
        object CanRequestCard : PlayerState()
        object RequestCard : PlayerState()
        object Hold : PlayerState()
        object Win : PlayerState()
        object Wait : PlayerState()
        object Lost : PlayerState()
    }
}
