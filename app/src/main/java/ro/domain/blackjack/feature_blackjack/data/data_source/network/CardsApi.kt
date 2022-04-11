package ro.domain.blackjack.feature_blackjack.data.data_source.network

import retrofit2.http.GET
import ro.domain.blackjack.feature_blackjack.domain.model.Card

interface CardsApi {

    @GET("/shuffle")
    suspend fun getShuffledCards(): MutableList<Card>
}