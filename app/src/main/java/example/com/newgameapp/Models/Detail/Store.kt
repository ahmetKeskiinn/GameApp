package example.com.newgameapp.Models.Detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Store {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("store")
    @Expose
    var store: Store__1? = null

}