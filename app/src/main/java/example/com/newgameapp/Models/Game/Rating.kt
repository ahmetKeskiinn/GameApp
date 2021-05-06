package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Rating {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("percent")
    @Expose
    var percent: Double? = null

}