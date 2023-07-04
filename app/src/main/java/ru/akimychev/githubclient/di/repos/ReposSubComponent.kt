package ru.akimychev.githubclient.di.repos

import dagger.Subcomponent
import ru.akimychev.githubclient.di.repos.module.ReposModule
import ru.akimychev.githubclient.mvp.presenter.ReposPresenter
import ru.akimychev.githubclient.ui.fragment.ReposFragment

@ReposScope
@Subcomponent(modules = [ReposModule::class])
interface ReposSubComponent {

    fun inject(githubUserReposPresenter: ReposPresenter)
    fun inject(fragment: ReposFragment)
}