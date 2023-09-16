package com.vnstudio.cleanarchitecturedemo

import java.io.Serializable

class Fork: Serializable {
    var id: Long? = null
    var name: String? = null
    var full_name: String? = null
    var owner: Owner? = Owner()
    var html_url: String? = null
    var description: String? = null
}
