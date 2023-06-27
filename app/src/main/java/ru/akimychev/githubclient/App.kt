package ru.akimychev.githubclient

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.akimychev.githubclient.mvp.model.database.AppDatabase
import ru.akimychev.githubclient.mvp.model.network.INetworkStatus
import ru.akimychev.githubclient.navigation.IScreens
import ru.akimychev.githubclient.navigation.Screens
import ru.akimychev.githubclient.ui.network.ConnectivityListener

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var networkStatus: INetworkStatus
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val screens: IScreens = Screens()

    override fun onCreate() {
        super.onCreate()
        instance = this

        networkStatus = ConnectivityListener(instance)

        AppDatabase.create(this)
    }
}