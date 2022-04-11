package ro.domain.blackjack.feature_blackjack.domain.repository

import ro.domain.blackjack.feature_blackjack.domain.model.Card

interface ShuffleCardsRepository {

    suspend fun getShuffledCardsFromNetwork(): MutableList<Card>

    fun cacheShuffledCards(shuffledCards: MutableList<Card>)

    fun getCard(): Card

    fun removeDealtCardFromCache()

    fun getShuffledCards(): MutableList<Card>

}