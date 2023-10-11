package com.vnstudio.cleanarchitecturedemo.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("forks")
data class Fork(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "full_name")
    var fullName: String? = null,
    @Embedded("owner")
    var owner: Owner? = null,
    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
) : Parcelable