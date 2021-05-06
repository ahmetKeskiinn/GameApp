package example.com.newgameapp.Models.Detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Platform__1 {
    @SerializedName("platform")
    @Expose
    var platform: Platform__2? = null

    @SerializedName("released_at")
    @Expose
    var releasedAt: String? = null

    @SerializedName("requirements")
    @Expose
    var requirements: Requirements? = null

}