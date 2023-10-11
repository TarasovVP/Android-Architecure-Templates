package com.vnstudio.cleanarchitecturedemo.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Owner(
    @ColumnInfo(name = "login")
    var login: String? = null,
    @ColumnInfo(name = "id")
    var ownerId: Long? = null,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,
) : Parcelable