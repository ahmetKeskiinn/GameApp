package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ParentPlatform {
    @SerializedName("platform")
    @Expose
    var platform: Platform__2? = null

}