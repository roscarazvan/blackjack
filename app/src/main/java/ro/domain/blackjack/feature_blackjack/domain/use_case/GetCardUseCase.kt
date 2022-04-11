package ro.domain.blackjack.feature_blackjack.domain.use_case

import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository
import ro.domain.blackjack.core.util.getUserState
import ro.domain.blackjack.core.util.sum
import javax.inject.Inject

class GetCardUseCase @Inject constructor(
    private val shuffleCardsRepository: ShuffleCardsRepository,
    private val playerRepository: PlayerRepository
) {

    operator fun invoke(): Player {

        val card = shuffleCardsRepository.getCard()

        shuffleCardsRepository.removeDealtCardFromCache()

        playerRepository.getUserSelectedCards().apply {
            add(card)
            val sum = this.sum()
            return Player(this, sum, sum.getUserState())
        }
    }
}