package ru.akimychev.githubclient.presenter

import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.view.MainView

class MainPresenter(private val view: MainView) {
    val btnOne = 1
    val btnTwo = 2
    val btnThree = 3

    private val model = CountersModel()

    fun counterClick(btn: Int) {
        when (btn) {
            btnOne -> {
                view.setBtnOneText(model.next(0).toString())
            }
            btnTwo -> {
                view.setBtnTwoText(model.next(1).toString())
            }
            btnThree -> {
                view.setBtnThreeText(model.next(2).toString())
            }
        }
    }
}