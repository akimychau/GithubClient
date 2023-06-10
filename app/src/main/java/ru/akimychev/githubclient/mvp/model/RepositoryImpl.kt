package ru.akimychev.githubclient.mvp.model

class RepositoryImpl : Repository {

    private val users = listOf(
        GithubUser("Cat"),
        GithubUser("Dog"),
        GithubUser("Mouse"),
        GithubUser("Pig"),
        GithubUser("Rat")
    )

    override fun getUsers() = users
}