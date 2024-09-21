<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/DemoObjectDB.kt
package com.vnteam.architecturetemplates
========
package com.vnstudio.cleanarchitecturedemo.models
>>>>>>>> 64049960 (Implement dependency inversion):app/src/main/java/com/vnteam/architecturetemplates/models/ForkDB.kt

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

@DatabaseTable
class DemoObjectDB: Serializable {
    @DatabaseField(columnName = "id")
    var id: Long? = null
    @DatabaseField(columnName = "name")
    var name: String? = null
    @DatabaseField(columnName = "full_name")
    var fullName: String? = null
    @DatabaseField(columnName = "owner")
    var owner: String? = null
    @DatabaseField(columnName = "html_url")
    var htmlUrl: String? = null
    @DatabaseField(columnName = "description")
    var description: String? = null
}
