package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class EsrbRating {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

}