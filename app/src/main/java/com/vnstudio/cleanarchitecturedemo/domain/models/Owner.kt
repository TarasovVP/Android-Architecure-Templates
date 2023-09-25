<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/models/Owner.kt
<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/Owner.kt
package com.vnteam.architecturetemplates
========
package com.vnstudio.cleanarchitecturedemo.models
>>>>>>>> 64049960 (Implement dependency inversion):app/src/main/java/com/vnteam/architecturetemplates/models/Owner.kt
========
package com.vnstudio.cleanarchitecturedemo.domain.models
>>>>>>>> 0af65b2c (Implement clean architecture folder structure):app/src/main/java/com/vnstudio/cleanarchitecturedemo/domain/models/Owner.kt

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