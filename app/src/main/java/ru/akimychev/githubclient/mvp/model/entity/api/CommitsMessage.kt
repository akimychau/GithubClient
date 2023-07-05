package ru.akimychev.githubclient.mvp.model.entity.api

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommitsMessage(
    @Expose val message : String
): Parcelable
