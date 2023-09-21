package com.vnstudio.cleanarchitecturedemo.models

import com.squareup.moshi.Json
import com.vnstudio.cleanarchitecturedemo.models.Owner
import java.io.Serializable

class Fork: Serializable {
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
