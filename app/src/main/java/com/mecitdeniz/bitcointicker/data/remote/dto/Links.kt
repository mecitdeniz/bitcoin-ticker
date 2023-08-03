package com.mecitdeniz.bitcointicker.data.remote.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class Links (
    val homepage: List<String>,

    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,

    @SerializedName("official_forum_url")
    val officialForumURL: List<String>,

    @SerializedName("chat_url")
    val chatURL: List<String>,

    @SerializedName("announcement_url")
    val announcementURL: List<String>,

    @SerializedName("twitter_screen_name")
    val twitterScreenName: String,

    @SerializedName("facebook_username")
    val facebookUsername: String,

    @SerializedName("bitcointalk_thread_identifier")
    val bitcointalkThreadIdentifier: JsonElement? = null,

    @SerializedName("telegram_channel_identifier")
    val telegramChannelIdentifier: String,

    @SerializedName("subreddit_url")
    val subredditURL: String,

    @SerializedName("repos_url")
    val reposURL: ReposURL
)
