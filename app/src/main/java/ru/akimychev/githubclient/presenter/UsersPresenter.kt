package ru.akimychev.githubclient.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.utils.btnOne
import ru.akimychev.githubclient.utils.btnThree
import ru.akimychev.githubclient.utils.btnTwo
import ru.akimychev.githubclient.view.UsersView

class UsersPresenter(private val model: CountersModel, private val router: Router) :
    MvpPresenter<UsersView>() {

    fun counterClickBtnOne() {
        viewState.setBtnOneText(model.next(btnOne).toString())
    }

    fun counterClickBtnTwo() {
        viewState.setBtnTwoText(model.next(btnTwo).toString())
    }

    fun counterClickBtnThree() {
        viewState.setBtnThreeText(model.next(btnThree).toString())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}