package ro.domain.blackjack.feature_blackjack.domain.use_case

import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository
import ro.domain.blackjack.core.util.getUserState
import ro.domain.blackjack.core.util.sum
import javax.inject.Inject

class RestartUseCase @Inject constructor(
    private val shuffleCardsRepository: ShuffleCardsRepository,
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(): List<Player> {

        shuffleCardsRepository.getShuffledCards().clear()
        playerRepository.apply {
            getUserSelectedCards().clear()
            getComputerSelectedCards().clear()
        }

        shuffleCardsRepository.getShuffledCardsFromNetwork().apply {
            val userCards = subList(0, 2)
            val sumUser = userCards.sum()

            val computerCards = subList(2, 4)
            val sumComputer = computerCards.sum()

            playerRepository.saveUserSelectedCards(userCards)
            playerRepository.saveComputerSelectedCards(computerCards)

            shuffleCardsRepository.cacheShuffledCards(subList(4, size ))

            return listOf(
                Player(userCards, sumUser, sumUser.getUserState()),
                Player(computerCards, sumComputer, Player.PlayerState.Wait)
            )
        }
    }

}