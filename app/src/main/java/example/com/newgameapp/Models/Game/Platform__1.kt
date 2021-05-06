package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Platform__1 {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("image")
    @Expose
    var ımage: Any? = null

    @SerializedName("year_end")
    @Expose
    var yearEnd: Any? = null

    @SerializedName("year_start")
    @Expose
    var yearStart: Int? = null

    @SerializedName("games_count")
    @Expose
    var gamesCount: Int? = null

    @SerializedName("image_background")
    @Expose
    var ımageBackground: String? = null

}