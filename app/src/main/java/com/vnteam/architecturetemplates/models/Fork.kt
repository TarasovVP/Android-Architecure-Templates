<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/DemoObject.kt
package com.vnteam.architecturetemplates
========
package com.vnstudio.cleanarchitecturedemo.models
>>>>>>>> 64049960 (Implement dependency inversion):app/src/main/java/com/vnteam/architecturetemplates/models/Fork.kt

import com.squareup.moshi.Json
import com.vnstudio.cleanarchitecturedemo.models.Owner
import java.io.Serializable

class DemoObject: Serializable {
    @Json(name = "id")
    var id: Long? = null
    @Json(name = "name")
    var name: String? = null
    @Json(name = "full_name")
    var fullName: String? = null
    @Json(name = "owner")
    var owner: Owner? = null
    @Json(name = "html_url")
    var htmlUrl: String? = null
    @Json(name = "description")
    var description: String? = null
}
