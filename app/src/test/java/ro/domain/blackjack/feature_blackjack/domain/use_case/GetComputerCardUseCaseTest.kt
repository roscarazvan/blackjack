package ro.domain.blackjack.feature_blackjack.domain.use_case

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ro.domain.blackjack.feature_blackjack.domain.model.Card
import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.model.Suit
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository


class GetComputerCardUseCaseTest {

    private val shuffleCardsRepository = Mockito.mock(ShuffleCardsRepository::class.java)
    private val playerRepository = Mockito.mock(PlayerRepository::class.java)

    lateinit var getComputerCardUseCase: GetComputerCardUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getComputerCardUseCase = GetComputerCardUseCase(shuffleCardsRepository, playerRepository)
    }

    @Test
    fun `invoke when user cards sum is less then computer sum and less then 21 computer wins`() {
        `when`(playerRepository.getUserSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "K"),
                Card(Suit.SPADES, "K")
            )
        )
        `when`(playerRepository.getComputerSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "9"),
                Card(Suit.SPADES, "K"),
                Card(Suit.SPADES, "2")
            )
        )

        val computer = getComputerCardUseCase.invoke()
        assertTrue(computer.playerState == Player.PlayerState.Win)
        assertTrue(computer.totalPoints == 21)
    }

    @Test
    fun `invoke when computer cards sum is less then user sum then computer gets another card`() {
        `when`(playerRepository.getUserSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "K"),
                Card(Suit.SPADES, "K")
            )
        )
        `when`(playerRepository.getComputerSelectedCards()).thenReturn(
            mutableListOf(
                Card(Suit.SPADES, "9"),
                Card(Suit.SPADES, "K"),
            )
        )

        `when`(shuffleCardsRepository.getShuffledCards()).thenReturn(mutableListOf(
            Card(Suit.SPADES, "4"),
            Card(Suit.SPADES, "7"),
        ))

        val computer = getComputerCardUseCase.invoke()
        assertTrue(computer.playerState == Player.PlayerState.Lost)
        assertTrue(computer.totalPoints == 23)
    }

}