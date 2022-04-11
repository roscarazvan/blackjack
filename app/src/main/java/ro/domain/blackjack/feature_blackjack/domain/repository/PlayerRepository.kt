package ro.domain.blackjack.feature_blackjack.domain.repository

import ro.domain.blackjack.feature_blackjack.domain.model.Card

interface PlayerRepository {

   fun saveUserSelectedCards(userSelectedCards: MutableList<Card>)

   fun saveComputerSelectedCards(userSelectedCards: MutableList<Card>)

   fun getUserSelectedCards(): MutableList<Card>

   fun getComputerSelectedCards(): MutableList<Card>

}