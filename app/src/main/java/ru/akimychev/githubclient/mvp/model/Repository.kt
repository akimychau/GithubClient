package ru.akimychev.githubclient.mvp.model

fun interface Repository {

    fun getUsers(): List<GithubUser>
}