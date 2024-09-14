package com.vnteam.architecturetemplates.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("forks")
data class Fork(
    @PrimaryKey
    @SerializedName("id")
    var id: Long? = null,
    var name: String? = null,
    @SerializedName("full_name")
    var fullName: String? = null,
    @Embedded("owner")
    var owner: Owner? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
) : Parcelable
