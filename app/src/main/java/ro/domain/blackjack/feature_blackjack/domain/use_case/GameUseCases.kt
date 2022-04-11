package ro.domain.blackjack.feature_blackjack.domain.use_case

data class GameUseCases(
    val initialiseGameUseCase: InitialiseGameUseCase,
    val getUserCardUseCase: GetCardUseCase,
    val getComputerCardUseCase: GetComputerCardUseCase,
    val restartUseCase: RestartUseCase
)
