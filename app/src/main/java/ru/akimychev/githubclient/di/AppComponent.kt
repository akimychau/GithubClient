package ru.akimychev.githubclient.di

import dagger.Component
import ru.akimychev.githubclient.di.modules.ApiModule
import ru.akimychev.githubclient.di.modules.AppModule
import ru.akimychev.githubclient.di.modules.CacheModule
import ru.akimychev.githubclient.di.modules.CiceroneModule
import ru.akimychev.githubclient.di.modules.ImageLoaderModule
import ru.akimychev.githubclient.di.modules.RepositoriesModule
import ru.akimychev.githubclient.mvp.presenter.ForksCountPresenter
import ru.akimychev.githubclient.mvp.presenter.MainPresenter
import ru.akimychev.githubclient.mvp.presenter.ReposPresenter
import ru.akimychev.githubclient.mvp.presenter.UsersPresenter
import ru.akimychev.githubclient.ui.activity.MainActivity
import ru.akimychev.githubclient.ui.adapter.UsersRVAdapter
import ru.akimychev.githubclient.ui.fragment.ReposFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class,
        RepositoriesModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(githubUsersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)

    fun inject(githubUserReposPresenter: ReposPresenter)
    fun inject(fragment: ReposFragment)

    fun inject(forksCountPresenter: ForksCountPresenter)
}