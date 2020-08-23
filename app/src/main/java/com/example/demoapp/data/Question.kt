package com.example.demoapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question {
    @SerializedName("error_message")
    @Expose
    var errorMessage: String? = null

    @SerializedName("mandatory")
    @Expose
    var mandatory: Int? = null

    @SerializedName("placeholder")
    @Expose
    var placeholder: String? = null

    @SerializedName("multi_option_flag")
    @Expose
    var multiOptionFlag: Int? = null

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("answers")
    @Expose
    var answers: String? = null

    @SerializedName("image_flag")
    @Expose
    var imageFlag: Int? = null

    @SerializedName("values")
    @Expose
    var values: MutableList<Value>? = null

    @SerializedName("multi_select_flag")
    @Expose
    var multiSelectFlag: Int? = null

    @SerializedName("images")
    @Expose
    var images: List<Any>? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("question_id")
    @Expose
    var questionId: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}