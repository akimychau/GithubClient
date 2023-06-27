package ru.akimychev.githubclient.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimychev.githubclient.mvp.model.entity.GithubUser
import ru.akimychev.githubclient.mvp.model.entity.GithubUserRepos

interface IScreens {

    fun users(): FragmentScreen

    fun details(user: GithubUser): FragmentScreen

    fun forks(forksCount: GithubUserRepos):
            FragmentScreen
}