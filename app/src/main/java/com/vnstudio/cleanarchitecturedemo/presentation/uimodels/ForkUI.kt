package com.vnstudio.cleanarchitecturedemo.presentation.uimodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForkUI(
    var id: Long? = null,
    var name: String? = null,
    var fullName: String? = null,
    var owner: OwnerUI? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
) : Parcelable