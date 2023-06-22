package ru.akimychev.githubclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.mvp.view.ForksCountView

class ForksCountPresenter (
    private val router: Router
) : MvpPresenter<ForksCountView>() {

    fun show(repos: GithubUserRepos) {
        viewState.showNumberOfForks(repos.forksCount.toString())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}