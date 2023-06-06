package ru.akimychev.githubclient.presenter

import ru.akimychev.githubclient.R
import ru.akimychev.githubclient.model.CountersModel
import ru.akimychev.githubclient.view.MainView

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            R.id.btn_counter1 -> {
                val nextValue = model.next(0)
                view.setBtnText(0, nextValue.toString())
            }
            R.id.btn_counter2 -> {
                val nextValue = model.next(1)
                view.setBtnText(1, nextValue.toString())
            }
            R.id.btn_counter3 -> {
                val nextValue = model.next(2)
                view.setBtnText(2, nextValue.toString())
            }
        }
    }
}