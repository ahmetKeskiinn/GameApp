package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Year {
    @SerializedName("from")
    @Expose
    var from: Int? = null

    @SerializedName("to")
    @Expose
    var to: Int? = null

    @SerializedName("filter")
    @Expose
    var filter: String? = null

    @SerializedName("decade")
    @Expose
    var decade: Int? = null

    @SerializedName("years")
    @Expose
    var years: List<Year__1>? = null

    @SerializedName("nofollow")
    @Expose
    var nofollow: Boolean? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null

}