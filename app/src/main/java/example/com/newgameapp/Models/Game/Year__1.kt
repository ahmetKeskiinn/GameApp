package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Year__1 {
    @SerializedName("year")
    @Expose
    var year: Int? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("nofollow")
    @Expose
    var nofollow: Boolean? = null

}