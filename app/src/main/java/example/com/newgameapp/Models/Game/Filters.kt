package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Filters {
    @SerializedName("years")
    @Expose
    var years: List<Year>? = null

}