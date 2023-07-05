package ru.akimychev.githubclient.mvp.model.entity.api

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubCommits(
   @Expose val sha: String,
   @Expose val commit: CommitsMessage,
   @Expose val htmlUrl: String
): Parcelable
