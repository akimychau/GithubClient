package ru.akimychev.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.akimychev.githubclient.databinding.ItemCommitsBinding
import ru.akimychev.githubclient.mvp.presenter.list.ICommitsListPresenter
import ru.akimychev.githubclient.mvp.view.list.ICommitsItemView
import ru.akimychev.githubclient.utils.RV_INVALID_INDEX

class CommitsRVAdapter(
    private val presenter: ICommitsListPresenter
) :
    RecyclerView.Adapter<CommitsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCommitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(
        private val viewBinding: ItemCommitsBinding
    ) :
        RecyclerView.ViewHolder(viewBinding.root), ICommitsItemView {

        override fun setName(message: String) {
            viewBinding.commitName.text = message
        }

        override var pos = RV_INVALID_INDEX
    }
}