package ru.akimychev.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.akimychev.githubclient.databinding.ItemReposBinding
import ru.akimychev.githubclient.mvp.presenter.list.IReposListPresenter
import ru.akimychev.githubclient.mvp.view.list.IReposItemView
import ru.akimychev.githubclient.utils.RV_INVALID_INDEX

class ReposRVAdapter(
    private val presenter: IReposListPresenter
) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemReposBinding.inflate(
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
        private val viewBinding: ItemReposBinding
    ) :
        RecyclerView.ViewHolder(viewBinding.root), IReposItemView {

        override fun setName(name: String) {
            viewBinding.reposName.text = name
        }

        override var pos = RV_INVALID_INDEX
    }
}