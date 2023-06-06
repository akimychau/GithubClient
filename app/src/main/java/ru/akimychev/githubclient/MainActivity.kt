package ru.akimychev.githubclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.akimychev.githubclient.databinding.ActivityMainBinding
import ru.akimychev.githubclient.presenter.MainPresenter
import ru.akimychev.githubclient.view.MainView

class MainActivity : AppCompatActivity(), MainView {
    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter = MainPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        with(viewBinding){
            btnCounter1.setOnClickListener { presenter.counterClick(presenter.btnOne) }
            btnCounter2.setOnClickListener { presenter.counterClick(presenter.btnTwo) }
            btnCounter3.setOnClickListener { presenter.counterClick(presenter.btnThree) }
        }
    }

    override fun setBtnOneText(text: String) {
        viewBinding.btnCounter1.text = text
    }

    override fun setBtnTwoText(text: String) {
        viewBinding.btnCounter2.text = text
    }

    override fun setBtnThreeText(text: String) {
        viewBinding.btnCounter3.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}