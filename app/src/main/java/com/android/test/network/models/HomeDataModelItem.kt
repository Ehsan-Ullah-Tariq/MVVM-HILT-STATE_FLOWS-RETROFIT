package com.android.test.network.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeDataModelItem(
    @Json(name = "content")
    var content: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "imageUrl")
    var imageUrl: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "titile")
    var titile: String?,
    @Json(name = "type")
    var type: String?,
    @Json(name = "url")
    var url: Any?
)