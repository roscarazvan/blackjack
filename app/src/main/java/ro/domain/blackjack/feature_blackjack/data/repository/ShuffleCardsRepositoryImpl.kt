package ro.domain.blackjack.feature_blackjack.data.repository

import ro.domain.blackjack.feature_blackjack.data.data_source.network.CardsApi
import ro.domain.blackjack.feature_blackjack.domain.model.Card
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository
import javax.inject.Inject

class ShuffleCardsRepositoryImpl @Inject constructor(
    private val cardsApi: CardsApi
) : ShuffleCardsRepository {

    private var shuffledCards: MutableList<Card> = mutableListOf()

    override suspend fun getShuffledCardsFromNetwork() = cardsApi.getShuffledCards()

    override fun cacheShuffledCards(shuffledCards: MutableList<Card>) {
        this.shuffledCards.addAll(shuffledCards)
    }

    override fun getCard() = shuffledCards[0]

    override fun removeDealtCardFromCache() {
        shuffledCards.removeAt(0)
    }

    override fun getShuffledCards(): MutableList<Card> = shuffledCards
}