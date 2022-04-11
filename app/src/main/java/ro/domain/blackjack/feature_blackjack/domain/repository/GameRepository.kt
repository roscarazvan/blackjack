package ro.domain.blackjack.feature_blackjack.domain.repository

import ro.domain.blackjack.feature_blackjack.domain.model.Game

interface GameRepository {

    suspend fun getGames(): MutableList<Game>

    suspend fun saveGame(game: Game)

}