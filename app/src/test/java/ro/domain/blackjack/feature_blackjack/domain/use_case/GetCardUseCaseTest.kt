package ro.domain.blackjack.feature_blackjack.domain.use_case

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ro.domain.blackjack.feature_blackjack.domain.model.Card
import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.model.Suit
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository

class GetCardUseCaseTest {

    private val shuffleCardsRepository = Mockito.mock(ShuffleCardsRepository::class.java)
    private val playerRepository = Mockito.mock(PlayerRepository::class.java)

    lateinit var getCardUseCase: GetCardUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCardUseCase = GetCardUseCase(shuffleCardsRepository, playerRepository)
    }

    @Test
    fun `invoke when dealt card sum is larger than 21 set player state to lost`() {
        `when`(shuffleCardsRepository.getCard()).thenReturn(Card(Suit.SPADES, "K"))
        `when`(playerRepository.getUserSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "K"),
                Card(Suit.SPADES, "K")
            )
        )
        val player = getCardUseCase.invoke()
        assertTrue(player.totalPoints == 30)
        assertTrue(player.playerState == Player.PlayerState.Lost)
        assertTrue(player.drawnCardsList.size == 3)
    }

    @Test
    fun `invoke when dealt card sum equals 21 set player state to win`() {
        `when`(shuffleCardsRepository.getCard()).thenReturn(Card(Suit.SPADES, "2"))
        `when`(playerRepository.getUserSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "9"),
                Card(Suit.SPADES, "K")
            )
        )
        val player = getCardUseCase.invoke()
        assertTrue(player.totalPoints == 21)
        assertTrue(player.playerState == Player.PlayerState.Win)
        assertTrue(player.drawnCardsList.size == 3)
    }

    @Test
    fun `invoke when dealt card verify card is removed`() {
        `when`(shuffleCardsRepository.getCard()).thenReturn(Card(Suit.SPADES, "K"))
        getCardUseCase.invoke()
        verify(shuffleCardsRepository).removeDealtCardFromCache()
    }

}