package ru.akimychev.githubclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.model.GithubUser
import ru.akimychev.githubclient.mvp.view.DetailsView

class DetailsPresenter(private val user: GithubUser?, private val router: Router) :
    MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        user?.let { viewState.init(it.login) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}