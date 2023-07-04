package ru.akimychev.githubclient.di.users

import dagger.Subcomponent
import ru.akimychev.githubclient.di.repos.ReposSubComponent
import ru.akimychev.githubclient.di.users.module.UsersModule
import ru.akimychev.githubclient.mvp.presenter.UsersPresenter
import ru.akimychev.githubclient.ui.adapter.UsersRVAdapter

@UsersScope
@Subcomponent(modules = [UsersModule::class])
interface UsersSubComponent {

    fun reposSubComponent(): ReposSubComponent

    fun inject(githubUsersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}