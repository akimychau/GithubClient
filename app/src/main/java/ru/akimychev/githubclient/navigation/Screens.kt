package ru.akimychev.githubclient.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos
import ru.akimychev.githubclient.ui.fragment.ForksCountFragment
import ru.akimychev.githubclient.ui.fragment.ReposFragment
import ru.akimychev.githubclient.ui.fragment.UsersFragment

object Screens {

    fun users() = FragmentScreen { UsersFragment.newInstance() }

    fun details(user: GithubUser) = FragmentScreen { ReposFragment.newInstance(user) }

    fun forks(forksCount: GithubUserRepos) =
        FragmentScreen { ForksCountFragment.newInstance(forksCount) }
}