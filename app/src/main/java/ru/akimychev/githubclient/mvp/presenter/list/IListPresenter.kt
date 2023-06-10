package ru.akimychev.githubclient.mvp.presenter.list

import ru.akimychev.githubclient.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}