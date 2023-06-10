package ru.akimychev.githubclient.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.mvp.model.CountersModel
import ru.akimychev.githubclient.mvp.model.GithubUser
import ru.akimychev.githubclient.mvp.model.RepositoryImpl
import ru.akimychev.githubclient.mvp.presenter.list.IUserListPresenter
import ru.akimychev.githubclient.mvp.view.UsersView
import ru.akimychev.githubclient.mvp.view.list.IUserItemView

class UsersPresenter(private val repositoryImpl: RepositoryImpl, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = { itemView ->
        //TODO
        }
    }

    private fun loadData() {

        val users = repositoryImpl.getUsers()

        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}