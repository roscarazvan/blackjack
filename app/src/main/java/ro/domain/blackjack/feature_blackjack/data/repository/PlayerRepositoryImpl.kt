package ro.domain.blackjack.feature_blackjack.data.repository

import ro.domain.blackjack.feature_blackjack.domain.model.Card
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository

class PlayerRepositoryImpl : PlayerRepository {

    private var userSelectedCards = mutableListOf<Card>()
    private var computerSelectedCards: MutableList<Card> = mutableListOf()

    override fun saveUserSelectedCards(userSelectedCards: MutableList<Card>) {
        this.userSelectedCards.addAll(userSelectedCards)
    }

    override fun saveComputerSelectedCards(userSelectedCards: MutableList<Card>) {
        this.computerSelectedCards.addAll(userSelectedCards)
    }

    override fun getUserSelectedCards(): MutableList<Card> = userSelectedCards

    override fun getComputerSelectedCards(): MutableList<Card> = computerSelectedCards
}