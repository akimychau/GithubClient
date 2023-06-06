package ru.akimychev.githubclient

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.akimychev.githubclient.databinding.ActivityMainBinding
import ru.akimychev.githubclient.presenter.MainPresenter
import ru.akimychev.githubclient.view.MainView

class MainActivity : AppCompatActivity(), MainView {
    private var viewBinding: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        viewBinding?.btnCounter1?.setOnClickListener(listener)
        viewBinding?.btnCounter2?.setOnClickListener(listener)
        viewBinding?.btnCounter3?.setOnClickListener(listener)
    }

    override fun setBtnText(index: Int, text: String) {
        when (index) {
            0 -> viewBinding?.btnCounter1?.text = text
            1 -> viewBinding?.btnCounter2?.text = text
            2 -> viewBinding?.btnCounter3?.text = text
        }
    }
}