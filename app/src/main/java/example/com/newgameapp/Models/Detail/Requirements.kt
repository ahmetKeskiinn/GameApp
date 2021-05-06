package example.com.newgameapp.Models.Detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Requirements {
    @SerializedName("minimum")
    @Expose
    var minimum: String? = null

    @SerializedName("recommended")
    @Expose
    var recommended: String? = null

}