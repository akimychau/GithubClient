package ru.akimychev.githubclient.presenter

import moxy.MvpPresenter
import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.utils.btnOne
import ru.akimychev.githubclient.utils.btnThree
import ru.akimychev.githubclient.utils.btnTwo
import ru.akimychev.githubclient.view.MainView

class MainPresenter(private val model: CountersModel) : MvpPresenter<MainView>() {
    fun counterClickBtnOne() {
        viewState.setBtnOneText(model.next(btnOne).toString())
    }

    fun counterClickBtnTwo() {
        viewState.setBtnTwoText(model.next(btnTwo).toString())
    }

    fun counterClickBtnThree() {
        viewState.setBtnThreeText(model.next(btnThree).toString())
    }
}