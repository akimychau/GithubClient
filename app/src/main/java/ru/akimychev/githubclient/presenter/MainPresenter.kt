package ru.akimychev.githubclient.presenter

import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.utils.btnOne
import ru.akimychev.githubclient.utils.btnThree
import ru.akimychev.githubclient.utils.btnTwo
import ru.akimychev.githubclient.view.MainView

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun counterClick(btn: Int) {
        when (btn) {
            btnOne -> {
                view.setBtnOneText(model.next(btnOne).toString())
            }
            btnTwo -> {
                view.setBtnTwoText(model.next(btnTwo).toString())
            }
            btnThree -> {
                view.setBtnThreeText(model.next(btnThree).toString())
            }
        }
    }
}