package ru.akimychev.githubclient.navigation

fun interface BackPressedListener {

    fun onBackPressed(): Boolean
}