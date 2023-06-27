package ru.akimychev.githubclient.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.akimychev.githubclient.mvp.view.IImageLoader
import ru.akimychev.githubclient.ui.image.GlideImageLoader
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun loadInto(): IImageLoader<ImageView> = GlideImageLoader()
}