package ru.akimychev.githubclient.di

import dagger.Component
import ru.akimychev.githubclient.di.modules.ApiModule
import ru.akimychev.githubclient.di.modules.AppModule
import ru.akimychev.githubclient.di.modules.CiceroneModule
import ru.akimychev.githubclient.di.modules.DatabaseModule
import ru.akimychev.githubclient.di.modules.ImageLoaderModule
import ru.akimychev.githubclient.di.users.UsersSubComponent
import ru.akimychev.githubclient.mvp.presenter.ForksCountPresenter
import ru.akimychev.githubclient.mvp.presenter.MainPresenter
import ru.akimychev.githubclient.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {

    fun usersSubComponent(): UsersSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(forksCountPresenter: ForksCountPresenter)
}