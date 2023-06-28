package ru.akimychev.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.akimychev.githubclient.mvp.model.entity.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposView : MvpView {

    fun init(user: GithubUser)
    fun updateList()
    fun loadAvatarAndLogin(user: GithubUser)
}