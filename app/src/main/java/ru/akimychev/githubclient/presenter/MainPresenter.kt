package ru.akimychev.githubclient.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.navigation.Screens
import ru.akimychev.githubclient.view.MainView

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}