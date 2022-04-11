package ro.domain.blackjack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ro.domain.blackjack.feature_blackjack.data.data_source.network.CardsApi
import ro.domain.blackjack.feature_blackjack.data.repository.PlayerRepositoryImpl
import ro.domain.blackjack.feature_blackjack.data.repository.ShuffleCardsRepositoryImpl
import ro.domain.blackjack.feature_blackjack.domain.repository.PlayerRepository
import ro.domain.blackjack.feature_blackjack.domain.repository.ShuffleCardsRepository
import ro.domain.blackjack.feature_blackjack.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideCardsApi(retrofit: Retrofit): CardsApi = retrofit.create(CardsApi::class.java)

    @Provides
    @Singleton
    fun providesShuffleCardsRepository(api: CardsApi): ShuffleCardsRepository = ShuffleCardsRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesPlayerRepository(): PlayerRepository = PlayerRepositoryImpl()

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: ShuffleCardsRepository, playerRepository: PlayerRepository): GameUseCases {
        return GameUseCases(
            initialiseGameUseCase = InitialiseGameUseCase(repository, playerRepository),
            getUserCardUseCase = GetCardUseCase(repository, playerRepository),
            restartUseCase = RestartUseCase(repository, playerRepository),
            getComputerCardUseCase = GetComputerCardUseCase(repository, playerRepository)
        )
    }
}