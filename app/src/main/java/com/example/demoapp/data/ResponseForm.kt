package com.example.demoapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseForm {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("course_process_id")
    @Expose
    var courseProcessId: Int? = null

    @SerializedName("questions")
    @Expose
    var questions: List<Question>? = null
}