package ru.akimychev.githubclient.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.ui.fragment.ForksCountFragment
import ru.akimychev.githubclient.ui.fragment.ReposFragment
import ru.akimychev.githubclient.ui.fragment.UsersFragment

class Screens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun details(user: GithubUser) = FragmentScreen { ReposFragment.newInstance(user) }

    override fun forks(forksCount: GithubUserRepos) =
        FragmentScreen { ForksCountFragment.newInstance(forksCount) }
}