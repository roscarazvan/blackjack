package ro.domain.blackjack.feature_blackjack.domain.use_case

import ro.domain.blackjack.core.util.getComputerState
import ro.domain.blackjack.core.util.getSublistWithSumAtLeast
import ro.domain.blackjack.core.util.sum
import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository
import javax.inject.Inject

class GetComputerCardUseCase @Inject constructor(
    private val shuffleCardsRepository: ShuffleCardsRepository,
    private val playerRepository: PlayerRepository
) {
    operator fun invoke(): Player {

        val userCardsSum = playerRepository.getUserSelectedCards().sum()
        val computerCardsSum = playerRepository.getComputerSelectedCards().sum()

        val cards = shuffleCardsRepository.getShuffledCards().getSublistWithSumAtLeast(computerCardsSum, userCardsSum)

        playerRepository.getComputerSelectedCards().apply {
            addAll(cards)
            val sum = this.sum()
            val computerState = sum.getComputerState()
            return Player(this, sum, computerState)
        }
    }

}