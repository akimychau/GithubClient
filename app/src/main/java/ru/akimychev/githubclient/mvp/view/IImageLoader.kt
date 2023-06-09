package ru.akimychev.githubclient.mvp.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}