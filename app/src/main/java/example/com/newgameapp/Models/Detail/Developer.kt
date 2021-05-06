package example.com.newgameapp.Models.Detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Developer {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("games_count")
    @Expose
    var gamesCount: Int? = null

    @SerializedName("image_background")
    @Expose
    var ımageBackground: String? = null

}