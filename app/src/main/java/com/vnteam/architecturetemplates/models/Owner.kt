<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/Owner.kt
package com.vnteam.architecturetemplates
========
package com.vnstudio.cleanarchitecturedemo.models
>>>>>>>> 64049960 (Implement dependency inversion):app/src/main/java/com/vnteam/architecturetemplates/models/Owner.kt

import com.squareup.moshi.Json
import java.io.Serializable

class Owner: Serializable {
    @Json(name = "login")
    var login: String? = null
    @Json(name = "id")
    var ownerId: Long? = null
    @Json(name = "avatar_url")
    var avatarUrl: String? = null
}