package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Platform {
    @SerializedName("platform")
    @Expose
    var platform: Platform__1? = null

    @SerializedName("released_at")
    @Expose
    var releasedAt: String? = null

    @SerializedName("requirements_en")
    @Expose
    var requirementsEn: Any? = null

    @SerializedName("requirements_ru")
    @Expose
    var requirementsRu: Any? = null

}