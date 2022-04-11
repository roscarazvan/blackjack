package ro.domain.blackjack.core.util

import ro.domain.blackjack.feature_blackjack.domain.model.Card
import ro.domain.blackjack.feature_blackjack.domain.model.Player

fun MutableList<Card>.sum(): Int {
    return this.map {
        it.value.getCardValue()
    }.sum()
}

fun Int.getComputerState(): Player.PlayerState{
    return when {

        this > 21 -> Player.PlayerState.Lost
        else -> Player.PlayerState.Win
    }
}

fun Int.getUserState(): Player.PlayerState {
    return when {
        this == 21 -> Player.PlayerState.Win
        this > 21 -> Player.PlayerState.Lost
        else -> Player.PlayerState.RequestCard
    }
}

fun MutableList<Card>.getSublistWithSumAtLeast(selectedCardsSum: Int, sum: Int): MutableList<Card> {
    return if (selectedCardsSum > sum) {
        mutableListOf()
    } else {
        var index = 0
        var totalSum = selectedCardsSum
        for (i in this.indices) {
            totalSum += Integer.valueOf(this[i].value.getCardValue())
            if (totalSum > sum) {
                index = i+1
                break
            }
        }
        this.subList(0, index)
    }
}

fun String.getCardValue(): Int {
    return when (this) {
        Card.ACE -> 11
        Card.JUVET -> 10
        Card.KING -> 10
        Card.QUEEN -> 10
        else -> Integer.valueOf(this)
    }
}