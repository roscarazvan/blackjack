package ro.domain.blackjack.feature_blackjack.presentation.game

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ro.domain.blackjack.databinding.FragmentHomeBinding
import ro.domain.blackjack.feature_blackjack.domain.model.Player.PlayerState.*
import ro.domain.blackjack.feature_blackjack.presentation.HomeScreenEvents
import ro.domain.blackjack.feature_blackjack.presentation.util.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {
        homeViewModel.apply {
            userLiveData.observe(viewLifecycleOwner, {
                it.also {
                    when (it.playerState) {
                        Hold, Win, Lost -> {
                            getViewBinding().hitButton.isEnabled = false
                            getViewBinding().holdButton.isEnabled = false
                        }
                        else -> {
                            getViewBinding().hitButton.isEnabled = true
                            getViewBinding().holdButton.isEnabled = true
                        }
                    }
                }
                getViewBinding().user.setView(it)
            })
            computerLiveData.observe(viewLifecycleOwner, {
                getViewBinding().computer.setView(it)
            })
            homeViewModel.onEvent(HomeScreenEvents.GetInitialGame)
        }


        getViewBinding().apply {
            hitButton.setOnClickListener {
                homeViewModel.onEvent(HomeScreenEvents.GetCard)
            }
            restart.setOnClickListener {
                homeViewModel.onEvent(HomeScreenEvents.Restart)
            }
            holdButton.setOnClickListener {
                hitButton.isEnabled = false
                homeViewModel.onEvent(HomeScreenEvents.UserHold)
            }
        }


    }
}