package ru.akimychev.githubclient.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.ui.fragment.ReposFragment
import ru.akimychev.githubclient.ui.fragment.UsersFragment

object Screens {

    fun users() = FragmentScreen { UsersFragment.newInstance() }
    fun details(user: GithubUser) = FragmentScreen { ReposFragment.newInstance(user) }
}