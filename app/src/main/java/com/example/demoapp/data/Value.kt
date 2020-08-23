package com.example.demoapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Value {
    @SerializedName("selected")
    @Expose
    var selected: Int? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: Any? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("value")
    @Expose
    var value: String? = "Please Select"

    override fun toString(): String {
        return value!!
    }

}