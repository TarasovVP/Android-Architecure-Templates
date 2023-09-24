<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/Owner.kt
package com.vnteam.architecturetemplates
========
package com.vnstudio.cleanarchitecturedemo.models
>>>>>>>> 64049960 (Implement dependency inversion):app/src/main/java/com/vnteam/architecturetemplates/models/Owner.kt

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