package ro.domain.blackjack.feature_blackjack.presentation.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.domain.blackjack.feature_blackjack.data.data_source.network.ExceptionParser
import ro.domain.blackjack.feature_blackjack.domain.model.CoroutineDispatcherProvider
import ro.domain.blackjack.feature_blackjack.domain.model.Player
import ro.domain.blackjack.feature_blackjack.domain.use_case.GameUseCases
import ro.domain.blackjack.feature_blackjack.presentation.HomeScreenEvents
import ro.domain.blackjack.feature_blackjack.presentation.HomeScreenEvents.*
import ro.domain.blackjack.feature_blackjack.presentation.util.GameUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameUseCases: GameUseCases,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Empty)
    val uiState: StateFlow<GameUiState> = _uiState

    val userLiveData = MutableLiveData<Player>()
    val computerLiveData = MutableLiveData<Player>()

    init {
        getInitialGame()
    }

    fun onEvent(homeScreenEvents: HomeScreenEvents) {
        when (homeScreenEvents) {
            GetInitialGame -> getInitialGame()
            GetCard -> getUserCard()
            Restart -> restartGame()
            UserHold -> getComputerCard()
        }
    }

    private fun getUserCard() {
        userLiveData.postValue(gameUseCases.getUserCardUseCase.invoke())
    }

    private fun getComputerCard() {
        computerLiveData.postValue(gameUseCases.getComputerCardUseCase.invoke())
    }

    private fun getInitialGame() {
        _uiState.value = GameUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val playersInitialState = gameUseCases.initialiseGameUseCase.invoke()
                userLiveData.postValue(playersInitialState[0])
                computerLiveData.postValue(playersInitialState[1])
            } catch (error: Exception) {
                _uiState.value = GameUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    private fun restartGame() {
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val players = gameUseCases.restartUseCase.invoke()
                userLiveData.postValue(players[0])
                computerLiveData.postValue(players[1])
            } catch (error: Exception) {
                _uiState.value = GameUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }
}