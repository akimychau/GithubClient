package ru.akimychev.githubclient

import android.app.Application
import ru.akimychev.githubclient.di.AppComponent
import ru.akimychev.githubclient.di.DaggerAppComponent
import ru.akimychev.githubclient.di.modules.AppModule
import ru.akimychev.githubclient.di.repos.IReposScopeContainer
import ru.akimychev.githubclient.di.repos.ReposSubComponent
import ru.akimychev.githubclient.di.users.IUsersScopeContainer
import ru.akimychev.githubclient.di.users.UsersSubComponent

class App : Application(), IUsersScopeContainer, IReposScopeContainer {

    lateinit var appComponent: AppComponent

    private var usersSubComponent: UsersSubComponent? = null

    private var reposSubComponent: ReposSubComponent? = null

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

    override fun initUserSubComponent() = appComponent.usersSubComponent().also {
        usersSubComponent = it
    }


    override fun releaseUserSubComponent() {
        usersSubComponent = null
    }

    override fun initReposSubComponent() = usersSubComponent?.reposSubComponent().also {
        reposSubComponent = it
    }

    override fun releaseReposSubComponent() {
        reposSubComponent = null
    }
}