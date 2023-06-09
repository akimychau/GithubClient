package ru.akimychev.githubclient.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimychev.githubclient.ui.UsersFragment

object Screens {

    fun users() = FragmentScreen { UsersFragment.newInstance() }
}