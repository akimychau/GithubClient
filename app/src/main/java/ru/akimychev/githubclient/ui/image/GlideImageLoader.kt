package ru.akimychev.githubclient.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.akimychev.githubclient.R
import ru.akimychev.githubclient.mvp.view.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .placeholder(R.drawable.baseline_person_24)
            .into(container)
    }
}