package ru.akimychev.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepos(
    @Expose val name: String,
    @Expose val forksCount: Int
) : Parcelable