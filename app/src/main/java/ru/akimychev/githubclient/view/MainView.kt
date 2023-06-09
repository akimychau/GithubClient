package ru.akimychev.githubclient.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setBtnOneText(text: String)
    fun setBtnTwoText(text: String)
    fun setBtnThreeText(text: String)
}