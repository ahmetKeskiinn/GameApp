package example.com.newgameapp.Models.Detail

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ParentPlatform {
    @SerializedName("platform")
    @Expose
    var platform: Platform? = null

}