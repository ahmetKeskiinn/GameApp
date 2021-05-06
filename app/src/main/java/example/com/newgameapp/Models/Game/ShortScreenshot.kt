package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ShortScreenshot {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("image")
    @Expose
    var ımage: String? = null
    override fun toString(): String {
        return "ShortScreenshot(ıd=$ıd, ımage=$ımage)"
    }

}