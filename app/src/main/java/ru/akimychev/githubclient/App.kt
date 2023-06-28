package ru.akimychev.githubclient

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.akimychev.githubclient.di.AppComponent
import ru.akimychev.githubclient.di.DaggerAppComponent
import ru.akimychev.githubclient.di.modules.AppModule
import ru.akimychev.githubclient.navigation.IScreens
import ru.akimychev.githubclient.navigation.Screens

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}