package ru.akimychev.githubclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.view.MainView
import ru.akimychev.githubclient.navigation.IScreens
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}