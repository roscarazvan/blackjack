package ro.domain.blackjack.feature_blackjack.presentation.util

import androidx.annotation.StringRes

sealed class GameUiState {
        object Empty : GameUiState()
        object Loading : GameUiState()
        class Loaded(val itemState: HomeItemUiState) : GameUiState()
        class Error(@StringRes val message: Int) : GameUiState()
    }