package ru.akimychev.githubclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.view.MainView
import ru.akimychev.githubclient.navigation.IScreens

class MainPresenter(
    private val router: Router,
    private val screen: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}