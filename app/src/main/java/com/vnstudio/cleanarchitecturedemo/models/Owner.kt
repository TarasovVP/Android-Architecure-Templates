package com.vnstudio.cleanarchitecturedemo.models

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Owner(
    var login: String? = null,
    @SerializedName("id")
    var ownerId: Long? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
) : Parcelable