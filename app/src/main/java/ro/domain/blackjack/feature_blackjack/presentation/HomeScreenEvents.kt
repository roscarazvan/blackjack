package ro.domain.blackjack.feature_blackjack.presentation

sealed class HomeScreenEvents {
    object GetInitialGame : HomeScreenEvents()
    object GetCard: HomeScreenEvents()
    object UserHold: HomeScreenEvents()
    object Restart : HomeScreenEvents()
}
